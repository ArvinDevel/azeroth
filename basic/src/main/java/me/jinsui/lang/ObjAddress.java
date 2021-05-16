package me.jinsui.lang;

/**
 * 对象被复制gc算法移动到新区域后，其地址是变化，如何不影响对象唯一的hashcode？
 * 对象锁膨胀期间，对象头内的hashcode是否被清除？
 * 如果是的话，那么对象头内的hashcode消失期间该对象被gc移到新区域后，其hashcode（如果是根据唯一地址计算的）还能保持唯一
 */
public class ObjAddress {
}
