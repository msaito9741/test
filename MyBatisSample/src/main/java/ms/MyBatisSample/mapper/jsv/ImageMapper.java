package ms.MyBatisSample.mapper.jsv;

import ms.MyBatisSample.entity.jsv.Image;

public interface ImageMapper {
    int selectMax();

    Image selectByPrimaryKey(String imageId);

    int deleteByPrimaryKey(String imageId);

    int insert(Image record);

}