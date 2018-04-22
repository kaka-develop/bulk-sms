package com.minhthanh.bulk.admin.controller;

import com.minhthanh.bulk.admin.form.BundleEditForm;
import com.minhthanh.bulk.admin.model.AjaxResponse;
import com.minhthanh.bulk.admin.service.BundleService;
import com.minhthanh.bulk.jpa.entities.Bundle;
import com.minhthanh.bulk.jpa.entities.BundleHistory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/bundle")
public class BundleController {

    @Autowired
    private BundleService bundleService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"), true);
        binder.registerCustomEditor(Date.class, editor);
    }

	@GetMapping(value="/")
	public String index(Model model) {

		model.addAttribute("lstBundles", bundleService.getListBundle());

		return "bundle/index";
	}

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {

        Bundle bundle = bundleService.findBundleById(id);

        BundleEditForm bundleEditForm = new BundleEditForm();
        BeanUtils.copyProperties(bundle, bundleEditForm);

        model.addAttribute("bundleEditForm", bundleEditForm);

        return "bundle/edit";
    }

    @GetMapping("/history/{id}")
    public String showHistoryForm(@PathVariable("id") long id, Model model) {

        List<BundleHistory> lstBundleHistory = bundleService.findAllPartnerByBundleId(id);

        model.addAttribute("lstBundleHistory", lstBundleHistory);

        return "bundle/history";
    }

    @PostMapping("/notify/{bundleId}/{partnerId}")
    public @ResponseBody AjaxResponse notifyBundleExtend(@PathVariable("bundleId") int bundleId,
                                                         @PathVariable("partnerId") int partnerId) {

        boolean isNotified = bundleService.notifyBundleExtend(bundleId, partnerId);

        if (isNotified) {
            return AjaxResponse.builder()
                    .addMessage("BBundle extend notification has been sent")
                    .addCode(HttpStatus.OK)
                    .build();
        }

        return AjaxResponse.builder()
                .addMessage("Bundle extend notification is error")
                .addCode(HttpStatus.NOT_FOUND)
                .build();
    }

    @GetMapping("/new")
    public String showRegisterForm(Model model) {

        model.addAttribute("bundleEditForm", new BundleEditForm());

        return "bundle/new";
    }

    @PostMapping("/update")
    public String update(@Valid BundleEditForm bundleEditForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "/bundle/edit";
        }

        Bundle validatedBundle = new Bundle();
        BeanUtils.copyProperties(bundleEditForm, validatedBundle);

        boolean isUpdated = bundleService.saveBundle(validatedBundle);

        if (isUpdated) {
            // Pass successful message back to view
            model.addAttribute("msg", "SUCCESSFUL");
        } else {
            model.addAttribute("msg", "ERROR");
        }

        return "/bundle/edit";

    }

    @PostMapping("/create")
    public String create(@Valid BundleEditForm bundleEditForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "/bundle/new";
        }

        Bundle validatedBundle = new Bundle();
        BeanUtils.copyProperties(bundleEditForm, validatedBundle);

        boolean isUpdated = bundleService.saveBundle(validatedBundle);

        if (isUpdated) {
            // Pass successful message back to view
            model.addAttribute("msg", "SUCCESSFUL");
        } else {
            model.addAttribute("msg", "ERROR");
        }

        return "/bundle/new";

    }

    @PostMapping("/delete/{id}")
    public @ResponseBody AjaxResponse delete(@PathVariable("id") long id) {
        bundleService.deleteBundle(id);
        return AjaxResponse.builder()
                .addMessage("SUCCESS")
                .addCode(HttpStatus.OK)
                .build();
    }
}
