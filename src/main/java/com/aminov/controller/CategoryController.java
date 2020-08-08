package com.aminov.controller;

import com.aminov.dto.CategoryDto;
import com.aminov.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CategoryController {

    private CategoryService<CategoryDto> categoryDtoService;

    @Autowired
    public void setCategoryService(CategoryService<CategoryDto> categoryDtoService){
        this.categoryDtoService = categoryDtoService;
    }

    @RequestMapping(value = "/admin/categories", method = RequestMethod.GET)
    public ModelAndView categoriesPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("categories");
        List<CategoryDto> categoryDtoList = categoryDtoService.allItems();
        modelAndView.addObject("categoriesList", categoryDtoList);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/categories/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteCategory(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/categories");
        CategoryDto categoryDto = categoryDtoService.getById(id);
        categoryDtoService.delete(categoryDto);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/categories/add", method = RequestMethod.POST)
    public ModelAndView addCategory(@ModelAttribute("category") CategoryDto categoryDto){
        ModelAndView modelAndView = new ModelAndView();
        categoryDtoService.add(categoryDto);
        modelAndView.setViewName("redirect:/categories");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/categories/edit", method = RequestMethod.POST)
    public ModelAndView editCategory(@ModelAttribute("category") CategoryDto categoryDto){
        ModelAndView modelAndView = new ModelAndView();
        categoryDtoService.edit(categoryDto);
        modelAndView.setViewName("redirect:/categories");
        return modelAndView;
    }

}
