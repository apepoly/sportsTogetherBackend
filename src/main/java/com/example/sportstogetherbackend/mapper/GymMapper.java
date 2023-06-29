package com.example.sportstogetherbackend.mapper;

import com.example.sportstogetherbackend.entity.Gym;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GymMapper {

    @Select("select * from gym where id = #{id}")
    Gym findGymById(Integer id);

    @Select("select * from gym where name like concat('%',#{name},'%') limit #{currIndex}, #{pageSize}")
    List<Gym> findGym(String text, int currIndex, int pageSize);

    @Select("select * from gym where category = #{category}")
    List<Gym> findGymByCategory(String category);

    @Insert("insert into gym(name,address,description) values(" +
            "#{name},#{address},#{description})")
    void insertGym(Gym gym);

    @Update("update gym set " +
            "name = #{name}," +
            "address = #{address}," +
            "description = #{description} " +
            "where id = #{id}")
    void updateGym(Gym gym);

    @Delete("delete from gym where id = #{id}")
    void deleteGym(Integer id);


}
