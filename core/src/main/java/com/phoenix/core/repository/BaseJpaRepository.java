package com.phoenix.core.repository;

import com.phoenix.core.entity.BaseEntity;
import com.phoenix.core.exception.RepositoryException;
import com.phoenix.core.model.query.SearchCriteria;
import com.phoenix.core.repository.specification.PredicateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @param <T>  the type of the entity to handle
 * @param <ID> the type of the entity's identifier
 *
 *             <p> Details: https://docs.spring.io/spring-data/data-jpa/docs/current/api/org/springframework/data/jpa/repository/support/SimpleJpaRepository.html</p>
 */
public interface BaseJpaRepository<T extends BaseEntity<ID>, ID extends Serializable> {
    //---------------------------------------------------------------------------------
    //--------------------------- COUNT GROUP
    //---------------------------------------------------------------------------------

    Specification<T> getSpecificationFromSearchCriteria(PredicateBuilder<T> predicateBuilder, List<SearchCriteria> conditions);

    Specification<T> getSpecificationFromSearchCriteria(List<SearchCriteria> conditions, Predicate.BooleanOperator booleanOperator);

    SimpleJpaRepository<T, ID> getJpaRepository();

    /**
     * @return the number of instances.
     */
    long count();

    /**
     * @param spec the Specification to count instances for. Can be null.
     * @return the number of instances that the given Specification will return.
     */
    long count(Specification<T> spec);

    //---------------------------------------------------------------------------------
    //--------------------------- DELETE GROUP
    //---------------------------------------------------------------------------------

    /**
     * @param entity must not be null.
     *               <p> Deletes a given entity. </p>
     */
    @Transactional
    void delete(T entity) throws RepositoryException;

    /**
     * Deletes all entities managed by the repository.
     */
    @Transactional
    void deleteAll();

    /**
     * @param entities must not be null. Must not contain null elements.
     */
    @Transactional
    void deleteAll(Iterable<? extends T> entities) throws RepositoryException;

    /**
     * @param ids must not be null. Must not contain null elements.
     *            <p> Deletes all instances of the type T with the given IDs. </p>
     */
    @Transactional
    void deleteAllById(Iterable<? extends ID> ids);

    /**
     * @param ids the ids of the entities to be deleted. Must not be null.
     *            <p> Deletes the entities identified by the given ids using a single query. This kind of operation
     *            leaves JPAs first level cache and the database out of sync. Consider flushing the EntityManager before calling
     *            this method. </p>
     */
    @Transactional
    void deleteAllByIdInBatch(Iterable<ID> ids);

    /**
     * <p>Deletes all entities in a batch call.</p>
     */
    @Transactional
    void deleteAllInBatch();

    /**
     * @param entities entities to be deleted. Must not be null.
     *                 <p> Deletes the given entities in a batch which means it will create a single query. This kind
     *                 of operation leaves JPAs first level cache and the database out of sync. Consider flushing the
     *                 EntityManager before calling this method.</p>
     */
    @Transactional
    void deleteAllInBatch(Iterable<T> entities);

    /**
     * @param id must not be null.
     *           <p> Deletes the entity with the given id.</p>
     */
    @Transactional
    void deleteById(ID id) throws RepositoryException;

    //---------------------------------------------------------------------------------
    //--------------------------- FIND GROUP
    //---------------------------------------------------------------------------------

    /**
     * @param id must not be null.
     * @return true if an entity with the given id exists, false otherwise.
     * <p> Returns whether an entity with the given id exists. </p>
     */
    boolean existsById(ID id) throws RepositoryException;

    List<T> findAll();

    /**
     * @param pageable pageable
     * @return a page of entities
     *
     * <p> Returns all entities sorted by the given options. </p>
     */
    Page<T> findAll(Pageable pageable);

    /**
     * @param sort sort
     * @return all entities sorted by the given options
     *
     * <p> Returns all entities sorted by the given options. </p>
     */
    List<T> findAll(Sort sort);

    /**
     * @param spec can be null.
     * @return never null.
     *
     * <p> Returns all entities matching the given Specification. </p>
     */
    List<T> findAll(Specification<T> spec);

    /**
     * @param spec     can be null.
     * @param pageable must not be null.
     * @return never null.
     *
     * <p> Returns a Page of entities matching the given Specification. </p>
     */
    Page<T> findAll(Specification<T> spec, Pageable pageable);

    /**
     * @param spec can be null.
     * @param sort must not be null.
     * @return never null.
     *
     * <p> Returns all entities matching the given Specification and Sort. </p>
     */
    List<T> findAll(Specification<T> spec, Sort sort);

    /**
     * @param ids must not be null nor contain any null values.
     * @return guaranteed to be not null. The size can be equal or less than the number of given ids.
     *
     * <ul>
     *     <li> Returns all instances of the type T with the given IDs. </li>
     *     <li> If some or all ids are not found, no entities are returned for these IDs. </li>
     *     <li> Note that the order of elements in the result is not guaranteed. </li>
     * </ul>
     */
    List<T> findAllById(Iterable<ID> ids) throws RepositoryException;

    /**
     * @param id must not be null.
     * @return the entity with the given id or Optional#empty() if none found.
     * <p> Retrieves an entity by its id. </p>
     */
    Optional<T> findById(ID id) throws RepositoryException;

    /**
     * @param spec can be null.
     * @return never null.
     *
     * <p> Returns a single entity matching the given Specification or Optional.empty() if none found. </p>
     */
    Optional<T> findOne(Specification<T> spec);

    T getById(ID id);

    //---------------------------------------------------------------------------------
    //--------------------------- SAVE GROUP
    //---------------------------------------------------------------------------------

    /**
     * @param entity must not be null.
     * @return the saved entity; will never be null.
     *
     * <p> Saves a given entity. Use the returned instance for further operations as the save operation might have
     * changed the entity instance completely. </p>
     */
    @Transactional
    <S extends T> S save(S entity);


    /**
     * @param entity entity to be saved. Must not be null.
     * @return the saved entity
     *
     * <p> Saves an entity and flushes changes instantly. </p>
     */
    @Transactional
    <S extends T> S saveAndFlush(S entity);

    /**
     * @param entities entities to be saved. Must not be null.
     * @return the saved entities
     *
     * <p> Saves all entities and flushes changes instantly. </p>
     */
    @Transactional
    <S extends T> List<S> saveAllAndFlush(Iterable<S> entities);

    /**
     * Flushes all pending changes to the database.
     */
    @Transactional
    void flush();

}
