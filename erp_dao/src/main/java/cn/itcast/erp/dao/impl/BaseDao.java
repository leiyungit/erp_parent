package cn.itcast.erp.dao.impl;

import cn.itcast.erp.dao.IBaseDao;
import cn.itcast.erp.entity.Dep;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseDao<T> extends HibernateDaoSupport implements IBaseDao<T> {

    private Class<T> entityClass;

    public BaseDao() {
        // 通过子类来获取父类。 得到之类的class的父一级Class
        Type genericSuperclass = getClass().getGenericSuperclass();
        // 转成参数化的类型。 可以得到泛型类型的所有Type
        ParameterizedType parameterizedType = (ParameterizedType)genericSuperclass;
        // 得到参数类型的数组。 得到所有的泛型类型。  返回一个表示此类型的实际类型参数的数组 Type对象。
        Type[] types = parameterizedType.getActualTypeArguments();
        // 转成class类型 取第一个泛型的类型。   Type 类下只有一个之类 Class，强制转换
        this.entityClass = (Class<T>)types[0];
    }


    public List<T> getList() {
        return (List<T>) this.getHibernateTemplate().loadAll(entityClass);
    }

    /**
     * 条件查询
     *
     * @param t1
     * @return
     */
    public List<T> getList(T t1, int firstResult, int maxResults) {
        //DetachedCriteria dc = getDetachedCriteria(t1);
        return this.listByPage(t1,null,null,firstResult,maxResults);
    }

    /**
     * 条件查询列表
     * @param t1
     * @return
     */
    public List<T> getList(T t1,T t2,Object param){

        DetachedCriteria dc = getDetachedCriteria(t1, t2, param);
        return (List<T>) getHibernateTemplate().findByCriteria(dc);
    }

    @Override
    public List<T> listByPage(T t1, T t2, Object param, int firstResult, int maxResults) {
        DetachedCriteria dc = getDetachedCriteria(t1, t2, param);
        return (List<T>) this.getHibernateTemplate().findByCriteria(dc,firstResult,maxResults);
    }

    /**
     * 记录条件查询的总记录数
     *
     * @param t1
     * @return
     */
    @Override
    public long getCount(T t1) {
        DetachedCriteria dc = getDetachedCriteria(t1,null,null);
        dc.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>)this.getHibernateTemplate().findByCriteria(dc);
        return list.get(0);
    }

    @Override
    public long getCount(T t1, T t2, Object param) {
        DetachedCriteria dc = getDetachedCriteria(t1,t2,param);
        dc.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>)this.getHibernateTemplate().findByCriteria(dc);
        return list.get(0);
    }

    @Override
    public void add(T t) {
        System.out.println(t);
        // this.getHibernateTemplate().setCheckWriteOperations(false);
        this.getHibernateTemplate().save(t);
    }

    // todo: 需要判断是否有关联，比如删除部门前，如果该部门已经被员工引用了，则不允许删除
    @Override
    public void delete(Long uuid) {
         /*Dep t = new Dep();
        t.setUuid(uuid);*/
        T t = this.getHibernateTemplate().get(entityClass, uuid);
        this.getHibernateTemplate().delete(t);
    }

    @Override
    public T get(Long uuid) {
        return this.getHibernateTemplate().get(entityClass,uuid);
    }

    @Override
    public void update(T t) {
        this.getHibernateTemplate().update(t);
    }

    /**
     * 构建查询条件
     * @param t1
     * @param t2
     * @param param
     * @return
     */
    public DetachedCriteria getDetachedCriteria(T t1,T t2,Object param){

        return null;
    }

    /**
     * 需子类重写
     * @param t1
     * @return
     */
    public DetachedCriteria getDetachedCriteria(T t1){
        /*DetachedCriteria dc = DetachedCriteria.forClass(T.class);
        if(null != t1){
            if(null != t1.getName() && t1.getName().trim().length()>0){
                //MatchMode.ANYWHERE => % %
                //MatchMode.END =>      %name
                //MatchMode.START  =>   name%
                dc.add(Restrictions.like("name",t1.getName(), MatchMode.ANYWHERE));
            }
            if(null != t1.getTele() && t1.getTele().trim().length() >0){
                dc.add(Restrictions.like("tele",t1.getTele(),MatchMode.ANYWHERE));
            }
        }*/
        return null;
    }
}
