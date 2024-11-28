package rami.generic.services;


import rami.generic.entities.base.BaseEntity;

public interface LoggingService {
  void info(String message, Long userId, Class<?> serviceClass);

  void error(String message, Long userId, Class<?> serviceClass, Throwable throwable);

  void createLog(Long userId, Class<?> serviceClass, Long createdEntityId, Class<? extends BaseEntity> newEntityClass);

  void createError(Long userId, Class<?> serviceClass, Long createdEntityId,
                   Class<? extends BaseEntity> newEntityClass, Throwable throwable);

  void readLog(Long userId, Class<?> serviceClass, Long readEntityId, Class<? extends BaseEntity> readEntityClass);

  void readError(Long userId, Class<?> serviceClass, Long readEntityId,
                 Class<? extends BaseEntity> readEntityClass, Throwable throwable);

  void updateLog(Long userId, Class<?> serviceClass, Long updatedEntityId, Class<? extends BaseEntity> updatedEntityClass);

  void updateError(Long userId, Class<?> serviceClass, Long updatedEntityId,
                   Class<? extends BaseEntity> updatedEntityClass, Throwable throwable);

  void deleteLog(Long userId, Class<?> serviceClass, Long deletedEntityId, Class<? extends BaseEntity> deletedEntityClass);

  void deleteError(Long userId, Class<?> serviceClass, Long deletedEntityId,
                   Class<? extends BaseEntity> deletedEntityClass, Throwable throwable);
}
