package demo.dto;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Author QAQCoder , Email:QAQCoder@qq.com
 * Create time 2019/10/14 15:34
 * Class description：分页DTO
 */

@Data
@ToString
public class PaginationDTO {
    private List<QuestionDTO> questionDTOS;

    private boolean showFirstPage;
    private boolean showPrevious;
    private Integer currentPage;
    private boolean showNext;
    private boolean showEndPage;

    private Integer totalPage;

    private Integer totalCount;

    private List<Integer> pages = new ArrayList<>(); //显示的页码数

    public void setPagination(Integer totalCount, Integer page, Integer size) {
//        Integer totalPage = (totalCount % size == 0 ? totalCount / size : totalCount / size + 1);

        this.totalCount = totalCount;

        //计算总页数
        if (totalCount % size == 0) totalPage = totalCount / size;
        else totalPage = totalCount / size + 1;

        //处理非法值
        if (page < 1) page = 1;
        if (page > totalPage) page = totalPage;

        currentPage = page;

        /**
         * 计算：左三 当前页 右三
         */
        if (page < 4) {
            for (int i = 1; i < page; i++) pages.add(i);
        } else {
            for (int i = page-3; i < page; i++) pages.add(i);
        }
        for (int i = page; i <= page+3 && i <= totalPage; i++) pages.add(i);

//        System.out.println(pages);

        //上一页按钮 是否要显示
        if (page == 1) showPrevious = false;
        else showPrevious = true;

        //下一页按钮 是否要显示
        if (page == totalPage) showNext = false;
        else showNext = true;

        //是否展示第一页按钮
        if (pages.contains(1)) showFirstPage = false;
        else showFirstPage = true;

        //是否展示最后一页按钮
        if (pages.contains(totalPage)) showEndPage = false;
        else showEndPage = true;
    }//
}
