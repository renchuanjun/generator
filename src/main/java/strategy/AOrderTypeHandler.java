package strategy;

/**
 * @Description:
 * @author: 任传君
 * @date: 2022.07.11
 */
public abstract class AOrderTypeHandler {

    /**
     * 一个订单类型做一件事
     *
     * @param dto 订单实体
     * @return 为了简单，返回字符串
     */
    abstract public String handler(OrderDTO dto);

}
