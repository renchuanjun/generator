package strategy;

/**
 * @Description:
 * @author: 任传君
 * @date: 2022.07.11
 */
public class OrderServiceImpl implements IOrderService{

    private HandlerContext handlerContext;


    public String orderHandler(OrderDTO dto) {
        AOrderTypeHandler instance = handlerContext.getInstance(dto.getType());
        return instance.handler(dto);
    }
}
