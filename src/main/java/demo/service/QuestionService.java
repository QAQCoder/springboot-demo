package demo.service;

import demo.dto.PaginationDTO;
import demo.dto.QuestionDTO;
import demo.exception.CustomErrorCode;
import demo.exception.CustomException;
import demo.mapper.QuestionExtendMapper;
import demo.mapper.QuestionMapper;
import demo.mapper.UserMapper;
import demo.model.Question;
import demo.model.QuestionExample;
import demo.model.User;
import demo.model.UserExample;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author QAQCoder , Email:QAQCoder@qq.com
 * Create time 2019/10/13 16:24
 * Class description：中间操作，写在service层里面
 * 查询Question列表，并根据creator属性，去查询user表，得到avatar，封装到DTO中【QuestionDTO】
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtendMapper questionExtendMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        //查询记录总数
        Integer totalCount = (int)questionMapper.countByExample(null);
        paginationDTO.setPagination(totalCount, page, size);

        //处理非法值
        if (page < 1) page = 1;
        if (page > paginationDTO.getTotalPage()) page = paginationDTO.getTotalPage();

        //计算起始查询偏移量
        Integer offset = (page - 1) * size;
        //得到查询页的数据
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(null, new RowBounds(offset, size));

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {

            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            if (user!= null) {
                //使用BeanUtils拷贝，美滋滋
                BeanUtils.copyProperties(question, questionDTO);
                questionDTO.setUser(user);
            }
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOList);

        return paginationDTO;
    }//

    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        //查询记录总数
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        List<Question> questionList = questionMapper.selectByExample(example);
        int totalCount = questionList.size();
        System.out.println("问题总数:" + totalCount);
        paginationDTO.setPagination(totalCount, page, size);

        //判断数据总数，为0时即可返回
        if (totalCount == 0) return paginationDTO;

        //处理非法值
        if (page < 1) page = 1;
        if (page > paginationDTO.getTotalPage()) page = paginationDTO.getTotalPage();

        //计算起始查询偏移量
        Integer offset = (page - 1) * size;
        //得到查询页的数据
        QuestionExample example1 = new QuestionExample();
        example1.createCriteria().andCreatorEqualTo(userId);
        List<Question> questionList1 = questionMapper.selectByExampleWithRowbounds(example1, new RowBounds(offset, size));

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList1) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            if (user!= null) {
                //使用BeanUtils拷贝，美滋滋
                BeanUtils.copyProperties(question, questionDTO);
                questionDTO.setUser(user);
            }
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestionDTOS(questionDTOList);

        return paginationDTO;
    }//

    public QuestionDTO findById(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);

        if (question == null) {
            throw new CustomException(CustomErrorCode.QUESTION_NOT_FOUND);
        }

        User user = userMapper.selectByPrimaryKey(question.getCreator());
        QuestionDTO questionDTO = new QuestionDTO();
        if (user!= null) {
            //使用BeanUtils拷贝，美滋滋
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
        }
        return questionDTO;
    }//

    public void createOrUpdate(Question question) {
        //判断是发布新问题 还是 更新问题
        if (question.getId() != null) {
            //更新
            System.out.println("更新问题");
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.updateByPrimaryKey(question);
        } else {
            //发布新问题
            System.out.println("发布新问题");
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            int insert = questionMapper.insert(question);
            if (insert != 1) {
                throw new CustomException(CustomErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }//

    public void incrementViewCount(Long id) {
        //先得到问题原本的阅读数
        Question oldQuestion = questionMapper.selectByPrimaryKey(id);
//        Question newQuestion = new Question();
//        newQuestion.setId(id);
        //增加1
        /*newQuestion.setBrowseCount(oldQuestion.getBrowseCount() + 1);
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andIdEqualTo(id);
        questionMapper.updateByExampleSelective(newQuestion, example);*/
        oldQuestion.setBrowseCount(1);
        questionExtendMapper.incrementViewCount(oldQuestion);
    }
}
