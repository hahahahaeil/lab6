package com.shen.mapper;

import com.shen.pojo.Orderdetail;
import java.util.List;

public interface OrderdetailMapper {

    void insertOrderdetail(Orderdetail orderdetail);

    Orderdetail getOrderdetailById(int id);

    List<Orderdetail> getAllOrderdetails();

    void updateOrderdetail(Orderdetail orderdetail);

    void deleteOrderdetail(int id);
}
