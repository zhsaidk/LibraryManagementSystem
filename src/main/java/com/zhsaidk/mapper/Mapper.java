package com.zhsaidk.mapper;

public interface Mapper<F, T>{
    T map(F obj);

    default T map(F from, T to){
        return to;
    }
}
