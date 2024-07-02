package com.sp.mapper;

import com.sp.model.domain.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;


public interface CateMapper {

    ArrayList<Category> getAllCate();
}
