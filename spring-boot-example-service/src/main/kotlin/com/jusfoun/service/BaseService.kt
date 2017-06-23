package com.jusfoun.service

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import com.jusfoun.dao.BaseMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired

/**
 * Service 基类
 * Created by liutiyang on 2017/5/18.
 */
open class BaseService<M : BaseMapper<T, E>, T, E> {

    @Autowired
    protected var mapper: M? = null

    fun countByExample(example: E): Int {
        return mapper!!.countByExample(example)
    }

    fun deleteByExample(example: E): Int {
        return mapper!!.deleteByExample(example)
    }

    fun deleteByPrimaryKey(id: String): Int {
        if (logger.isTraceEnabled) {
            logger.trace(mapper!!.javaClass.name + " delete record by id: " + id)
        }
        return mapper!!.deleteByPrimaryKey(id)
    }

    fun insert(record: T): Int {
        return mapper!!.insert(record)
    }

    fun insertSelective(record: T): Int {
        return mapper!!.insertSelective(record)
    }

    fun selectByExample(example: E): List<T> {
        return mapper!!.selectByExample(example)
    }

    fun selectByPrimaryKey(id: String): T {
        if (logger.isTraceEnabled) {
            logger.trace(mapper!!.javaClass.name + " select record by id: " + id)
        }
        return mapper!!.selectByPrimaryKey(id)
    }

    fun updateByExampleSelective(record: T, example: E): Int {
        return mapper!!.updateByExampleSelective(record, example)
    }

    fun updateByExample(record: T, example: E): Int {
        return mapper!!.updateByExample(record, example)
    }

    fun updateByPrimaryKeySelective(record: T): Int {
        return mapper!!.updateByPrimaryKeySelective(record)
    }

    fun updateByPrimaryKey(record: T): Int {
        return mapper!!.updateByPrimaryKey(record)
    }

    /**
     * 按条件查询一个对象
     * @param example
     * *
     * @return
     */
    fun selectOneByExample(example: E): T? {
        var t: T? = null
        val ts = mapper!!.selectByExample(example)
        if (ts != null && ts.size > 0) {
            t = ts[0]
        }
        return t
    }

    /**
     * 分页查询
     * @param example
     * *
     * @param count
     * *
     * @param pageSize
     * *
     * @return
     */
    fun selectPageByExample(example: E, count: Int, pageSize: Int): PageInfo<T> {
        PageHelper.startPage<Any>(count, pageSize)
        return PageInfo(mapper!!.selectByExample(example))
    }

    /**
     * 批量插入
     * @param collection
     * *
     * @return
     */
    fun batchInsertSelective(collection: Collection<T>): Int {
        return mapper!!.batchInsertSelectiveByProvider(collection)
    }

    /**
     * 批量更新
     * @param collection
     * *
     * @return
     */
    fun batchUpdateSelective(collection: Collection<T>): Int {
        return mapper!!.batchUpdateSelectiveByProvider(collection)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(BaseService::class.java)
    }

}
