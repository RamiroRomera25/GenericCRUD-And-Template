package rami.generic.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import rami.generic.entities.base.BaseEntity;
import rami.generic.services.LoggingService;

@Service
@Slf4j
public class LoggingServiceImpl implements LoggingService {

  @Override
  public void info(String message, Long userId, Class<?> serviceClass) {
    try (
        MDC.MDCCloseable classNameCloseable = MDC.putCloseable("className", serviceClass.getSimpleName());
        MDC.MDCCloseable usedIdCloseable    = MDC.putCloseable("userId", String.valueOf(userId));
    ) {
      log.info(message);
    }
  }

  @Override
  public void error(String message, Long userId, Class<?> serviceClass, Throwable throwable) {
    try (
        MDC.MDCCloseable classNameCloseable = MDC.putCloseable("className", serviceClass.getSimpleName());
        MDC.MDCCloseable usedIdCloseable    = MDC.putCloseable("userId", String.valueOf(userId));
    ) {
      log.error(message, throwable);
    }
  }

  private void crudInfo(String message, Long userId, Class<?> serviceClass, Long affectedEntityId,
                        Class<? extends BaseEntity> affectedEntityClass) {
    try (
        MDC.MDCCloseable classNameCloseable        = MDC.putCloseable("className", serviceClass.getSimpleName());
        MDC.MDCCloseable usedIdCloseable           = MDC.putCloseable("userId", String.valueOf(userId));
        MDC.MDCCloseable affectedEntityIdCloseable = MDC.putCloseable("affectedEntityId", String.valueOf(affectedEntityId));
        MDC.MDCCloseable newEntityClass            = MDC.putCloseable("affectedEntityClass", affectedEntityClass.getSimpleName())
    ) {
      log.info(message);
    }
  }

  private void crudError(String message, Long userId, Class<?> serviceClass, Long affectedEntityId,
                         Class<? extends BaseEntity> affectedEntityClass, Throwable throwable) {
    try (
        MDC.MDCCloseable classNameCloseable        = MDC.putCloseable("className", serviceClass.getSimpleName());
        MDC.MDCCloseable usedIdCloseable           = MDC.putCloseable("userId", String.valueOf(userId));
        MDC.MDCCloseable affectedEntityIdCloseable = MDC.putCloseable("affectedEntityId", String.valueOf(affectedEntityId));
        MDC.MDCCloseable newEntityClass            = MDC.putCloseable("affectedEntityClass", affectedEntityClass.getSimpleName())
    ) {
      log.error(message, throwable);
    }
  }

  @Override
  public void createLog(Long userId, Class<?> serviceClass, Long createdEntityId,
                        Class<? extends BaseEntity> newEntityClass) {
    String logMessage = "A " + newEntityClass.getSimpleName() + " with an ID = " + createdEntityId + " was created";
    crudInfo(logMessage, userId, serviceClass, createdEntityId, newEntityClass);
  }

  @Override
  public void createError(Long userId, Class<?> serviceClass, Long createdEntityId,
                          Class<? extends BaseEntity> newEntityClass, Throwable throwable) {
    String logMessage = "Error creating a " + newEntityClass.getSimpleName() + " with an ID = " + createdEntityId;
    crudError(logMessage, userId, serviceClass, createdEntityId, newEntityClass, throwable);
  }

  // TODO: 13/11/2024  Maybe I should add some way to log the filters used
  // TODO: 13/11/2024  Add the user id to the log on reads
  @Override
  public void readLog(Long userId, Class<?> serviceClass, Long readEntityId,
                      Class<? extends BaseEntity> readEntityClass) {
    String logMessage = "A " + readEntityClass.getSimpleName() + " with an ID = " + readEntityId + " was read";
    crudInfo(logMessage, userId, serviceClass, readEntityId, readEntityClass);
  }

  @Override
  public void readError(Long userId, Class<?> serviceClass, Long readEntityId,
                        Class<? extends BaseEntity> readEntityClass, Throwable throwable) {
    String logMessage = "Error reading a " + readEntityClass.getSimpleName() + " with an ID = " + readEntityId;
    crudError(logMessage, userId, serviceClass, readEntityId, readEntityClass, throwable);
  }

  @Override
  public void updateLog(Long userId, Class<?> serviceClass, Long updatedEntityId,
                        Class<? extends BaseEntity> updatedEntityClass) {
    String logMessage = "A " + updatedEntityClass.getSimpleName() + " with an ID = " + updatedEntityId + " was updated";
    crudInfo(logMessage, userId, serviceClass, updatedEntityId, updatedEntityClass);
  }

  @Override
  public void updateError(Long userId, Class<?> serviceClass, Long updatedEntityId,
                          Class<? extends BaseEntity> updatedEntityClass, Throwable throwable) {
    String logMessage = "Error updating a " + updatedEntityClass.getSimpleName() + " with an ID = " + updatedEntityId;
    crudError(logMessage, userId, serviceClass, updatedEntityId, updatedEntityClass, throwable);
  }

  @Override
  public void deleteLog(Long userId, Class<?> serviceClass, Long deletedEntityId,
                        Class<? extends BaseEntity> deletedEntityClass) {
    String logMessage = "A " + deletedEntityClass.getSimpleName() + " with an ID = " + deletedEntityId + " was logically deleted";
    crudInfo(logMessage, userId, serviceClass, deletedEntityId, deletedEntityClass);
  }

  @Override
  public void deleteError(Long userId, Class<?> serviceClass, Long deletedEntityId,
                          Class<? extends BaseEntity> deletedEntityClass, Throwable throwable) {
    String logMessage = "Error logically deleting a " + deletedEntityClass.getSimpleName() + " with an ID = " + deletedEntityId;
    crudError(logMessage, userId, serviceClass, deletedEntityId, deletedEntityClass, throwable);
  }

}
