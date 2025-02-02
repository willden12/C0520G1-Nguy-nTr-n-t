package vn.codegym.bog.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import vn.codegym.bog.model.Blog;
import vn.codegym.bog.model.Category;
import vn.codegym.bog.service.CategoryService;

import java.util.List;


@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<Category>> getCategoryList() {
        List<Category> categoryList = (List<Category>) categoryService.findAll();
        if (categoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Void> createNewCategory(@RequestBody Category category, UriComponentsBuilder builder) {
        categoryService.saveAndUpdate(category);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/categories/{id}").buildAndExpand(category.getCategoryId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<List<Blog>> viewBlog(@PathVariable("id") int id) {
        Category category = categoryService.findById(id);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<Blog> blogList = category.getCategoryBlogList();
        return new ResponseEntity<>(blogList, HttpStatus.OK);
    }
}
