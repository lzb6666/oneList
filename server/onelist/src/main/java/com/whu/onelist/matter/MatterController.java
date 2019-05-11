package com.whu.onelist.matter;

import com.whu.onelist.util.ResultMsg;
import com.whu.onelist.vo.Matter;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

/**
 * @author: create by zhong
 * @description: com.whu.onelist.matter
 * @date:2019/4/23
 */
@RequestMapping("/matter")
@RestController
public class MatterController {
    @Autowired
    public void setMatterService(MatterService matterService) {
        this.matterService = matterService;
    }

    private MatterService matterService;

    @PostMapping("")
    public ResponseEntity addMatter(Matter matter, HttpSession session){
        Long userID=(Long)session.getAttribute("userID");
        matter.setUserID(userID);
        if (matterService.addMatter(matter)){
            return new ResponseEntity<>(new ResultMsg(ResultMsg.Type.SUCCESS,"添加事件成功"), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ResultMsg(ResultMsg.Type.FAIL,"添加事件失败"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{matterID}")
    public ResponseEntity updateMatter(@PathVariable Long matterID, Matter matter, HttpSession session){
        Long userID=(Long)session.getAttribute("userID");
        matter.setUserID(userID);
        if (matterService.updateMatter(matter)){
            return new ResponseEntity<>(new ResultMsg(ResultMsg.Type.SUCCESS,"修改事件成功"), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ResultMsg(ResultMsg.Type.FAIL,"修改事件失败"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{matterID}")
    public ResponseEntity delete(@PathVariable Long matterID,HttpSession session){
        Long userID=(Long)session.getAttribute("userID");
        if (matterService.deleteMatter(matterID,userID)){
            return new ResponseEntity<>(new ResultMsg(ResultMsg.Type.SUCCESS,"删除事件成功"), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ResultMsg(ResultMsg.Type.FAIL,"删除事件失败"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{matterID}")
    public ResponseEntity getMatterByID(@PathVariable Long matterID){
        return new ResponseEntity<>(matterService.selectMatterByID(matterID),HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity getMattersByDate(Timestamp startTime, Timestamp endTime, HttpSession session){
        Long userID=(Long)session.getAttribute("userID");
        return new ResponseEntity<>(matterService.selectMattersByDate(startTime,endTime,userID),HttpStatus.OK);
    }

    @PostMapping("/auto")
    public ResponseEntity autoAdd(String content,HttpSession session){
        Long userID=(Long)session.getAttribute("userID");
        Matter matter=matterService.autoAdd(content,userID);
        if (matter!=null){
            return new ResponseEntity<>(matter, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ResultMsg(ResultMsg.Type.FAIL,"添加事件失败"), HttpStatus.BAD_REQUEST);
        }
    }
}
