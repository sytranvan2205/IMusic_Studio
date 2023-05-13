package com.imusicstudio.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.imusicstudio.entities.Order;
import com.imusicstudio.entities.OrderDetail;
import com.imusicstudio.service.OrderDetailService;
import com.imusicstudio.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderDetailService orderDetailService;

	@GetMapping("/admin/order")
	public String viewsOrderPage(Model model, @RequestParam("p") Optional<Integer> p) {

//		ModelAndView mv = new ModelAndView("admin/order");
//		List<Order> orders = orderService.getOrderShiped(limit, page-1);
//		Integer totalItem = orderService.getTotalItem();
//		Integer totalPage = (int) Math.ceil(totalItem/limit);
//		mv.addObject("totalPage", totalPage);
//		mv.addObject("page", page);
//		mv.addObject("limit", limit);
//		mv.addObject("orders", orders);
//		Long totalPrice = orderService.getTotalPriceByOrderId(9L);
		Pageable pageable = PageRequest.of(p.orElse(0), 10);
		Page<Order> page = orderService.getOrderByIsShipedAndStatus(pageable);
		model.addAttribute("page", page);
		return "admin/order";
	}

	@GetMapping("/admin/order/update-shiped/{id}")
	public String updateIsShiped(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		// Xóa danh mục
		try {
			orderService.updateIsShiped(id);
			redirectAttributes.addFlashAttribute("success", "Category deleted successfully");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("failed", "Failed to delete category");
		}
		 return "redirect:/admin/order";
	}
	
	@GetMapping("/admin/order-detail/{id}")
	public String viewsOrderDetailPage(Model model,@PathVariable Long id) {

		List<OrderDetail> orderDetails = orderDetailService.getAllOrderDetailByOrderId(id);
		Long totalPrice = orderService.getTotalPriceByOrderId(id);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("orderDetails", orderDetails);
		return "admin/order-detail";
	}
}
