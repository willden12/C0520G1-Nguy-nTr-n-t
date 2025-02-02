package com.codegym.casestady4.controllers;

import com.codegym.casestady4.model.AttachService;
import com.codegym.casestady4.model.Contract;
import com.codegym.casestady4.model.ContractDetail;
import com.codegym.casestady4.service.AttachServiceService;
import com.codegym.casestady4.service.ContractDetailService;
import com.codegym.casestady4.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Set;

@Controller
@RequestMapping("/contract-detail")
public class ContractDetailController {
    @Autowired
    private ContractDetailService contractDetailService;

    @Autowired
    private AttachServiceService attachServiceService;

    @Autowired
    private ContractService contractService;

    @ModelAttribute("attachServiceList")
    public Iterable<AttachService> getAttachServiceList() {
        return attachServiceService.findAll();
    }

    @ModelAttribute("contractList")
    public Iterable<Contract> getContractList() {
        return contractService.findAll();
    }

    @GetMapping("/detail/{id}")
    public String getContractDetailList(@PathVariable("id") long id, Model model) {
        Contract contract = contractService.findById(id);
        model.addAttribute("contract", contract);
        model.addAttribute("contractDetailList", contractDetailService.findContractDetailByContract(contract));
        Set<ContractDetail> temp = contract.getContractDetails();
        double attachFee = 0;
        for (ContractDetail contractDetail:temp) {
            attachFee += contractDetail.getContractDetailQuantity() * contractDetail.getAttachService().getAttachServiceCost();
        }
        model.addAttribute("attachFee", attachFee);
        return "contract-detail/contract-detail-list";
    }

    @GetMapping("/create")
    public String showCreateNewContractDetailForm(Model model) {
        model.addAttribute("contractDetail", new ContractDetail());
        return "contract-detail/contract-detail-create";
    }

    @PostMapping("/save")
    public String createNewContractDetail(@Validated ContractDetail contractDetail, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "contract-detail/contract-detail-create";
        } else {
            contractDetailService.save(contractDetail);
            redirectAttributes.addFlashAttribute("messInform", "Create Successful!!!");
            return "redirect:/contract";
        }
    }
}
