package cn.kgc.pcontroller;

import cn.kgc.domain.House;
import cn.kgc.domain.Users;
import cn.kgc.service.HouseService;
import cn.kgc.util.FileUploadUtil;
import cn.kgc.util.PageUtils;
import cn.kgc.util.SearchCondition;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;

@Controller(value = "houseController2")
@RequestMapping("/page/")
public class HouseController {

    @Autowired
    private HouseService houseService;

    /**
     * 发布房子信息
     * @param session
     * @param house
     * @param pfile
     * @return
     */
    @RequestMapping("addHouse")
    public String addHouse(HttpSession session,House house, @RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile) {
        try {

            String saveFileName = FileUploadUtil.upload(pfile);

            //2.将数据添加到数据库
            //修改house实体，添加额外的属性值
            //设置编号
            house.setId(System.currentTimeMillis()+"");
            //设置所属用户   重点理解
            Users user = (Users) session.getAttribute("loginInfo");
            house.setUserId(user.getId());
            //设置图片路径
            house.setPath(saveFileName);

            //调用业务实现添加
            houseService.addHouse(house);
            return "fabu";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * 展示用户发布的房源信息
     * @param pageUtils
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("showHouse")
    public String showHouse(PageUtils pageUtils, HttpSession session, Model model){
        //设置页大小
        pageUtils.setRows(3);
        //获取用户登入Id
        Users users = (Users) session.getAttribute("loginInfo");
        //调用业务获取数据
        PageInfo<House> pageInfo = houseService.getHouseByUser(users.getId(), pageUtils);
        model.addAttribute("pageInfo",pageInfo);
        return "guanli";
    }

    /**
     * 显示修改的房子信息
     * @return
     */
    @RequestMapping("editHouse")
    public String editHouse(String id,Model model){
        //调用业务获取数据
        House house = houseService.getHouse(id);
        //转发数据
        model.addAttribute("house",house);
        return "upfabu";
    }

    /**
     * 修改房子信息
     * @param house
     * @param pfile
     * @return
     */
    @RequestMapping("updateHouse")
    public String updateHouse(House house,String oldPicPath, @RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile) {
        try {
            //1.判断是否有图片引入
            if (!pfile.getOriginalFilename().equals("")){
                //System.out.println("上传图片")
                String saveFileName = FileUploadUtil.upload(pfile);
                //设置修改实体图片路径
                house.setPath(saveFileName);
                //删除旧图
                FileUploadUtil.deleteFile(oldPicPath);
            }
            //2.修改数据数据
            //调用业务实现添加
            houseService.updateHouse(house);
            return "redirect:showHouse";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    /**
     * 删除出租房
     * @param id
     * @return
     */
    @RequestMapping("deleteHouse")
        public String deleteHouse(String id){
            try{
                //调用业务删除数据
                int i = houseService.deleteHouse(id, 1); //1表示删除
                return "redirect:showHouse";
            }catch (Exception e){
                e.printStackTrace();
            }
            return "error";
    }

    /**
     * 回复出租房
     * @param id
     * @return
     */
    @RequestMapping("backHouse")
    public String backHouse(String id){
        try{
            //调用业务删除数据
            int i = houseService.deleteHouse(id, 0); //1表示不删除
            return "redirect:showHouse";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }

    @RequestMapping("/borswerHouse")
    public String borswerHouse(SearchCondition searchCondition,Model model){

        //设置大小
        searchCondition.setRows(5);
        //调用业务
        PageInfo<House> pageInfo = houseService.getBroswerHouse(searchCondition);
        //填充数据
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("searchCondition",searchCondition);
        return "list";
    }


}
