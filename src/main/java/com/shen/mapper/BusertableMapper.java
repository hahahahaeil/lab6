package com.shen.mapper;

import com.shen.pojo.Busertable;
import java.util.List;

public interface BusertableMapper {

    void insertBusertable(Busertable busertable);

    Busertable getBusertableById(int id);

    List<Busertable> getAllBusertables();

    void updateBusertable(Busertable busertable);

    void deleteBusertable(int id);
}
