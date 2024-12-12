package com.shen.mapper;

import com.shen.pojo.Orderbasetable;
import java.util.List;

public interface OrderbasetableMapper {

    void insertOrderbasetable(Orderbasetable orderbasetable);

    Orderbasetable getOrderbasetableById(int id);

    List<Orderbasetable> getAllOrderbasetables();

    void updateOrderbasetable(Orderbasetable orderbasetable);

    void deleteOrderbasetable(int id);
}
