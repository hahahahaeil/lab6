package com.shen.mapper;

import com.shen.pojo.Carttable;
import java.util.List;

public interface CarttableMapper {

    void insertCarttable(Carttable carttable);

    Carttable getCarttableById(int id);

    List<Carttable> getAllCarttables();

    void updateCarttable(Carttable carttable);

    void deleteCarttable(int id);
}
