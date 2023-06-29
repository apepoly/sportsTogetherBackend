package com.example.sportstogetherbackend.mapper;

import com.example.sportstogetherbackend.entity.GymImage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ImageMapper {
    @Select("select * from image where url = #{url}")
    GymImage findImageByUrl(String url);

    @Select("select * from image where gym_id = #{id}")
    List<GymImage> findImageByGymID(Integer id);
    @Delete("delete from img where url = #{url}")
    void deleteGymImg(String url);

    @Insert("insert into gym_img values(#{gymID},#{url})")
    void insertGymImg(GymImage gymImg);
}

