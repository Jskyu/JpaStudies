package jskstudies.jpashop.controller;

import jskstudies.jpashop.model.Member;
import jskstudies.jpashop.model.Order;
import jskstudies.jpashop.model.item.Item;
import jskstudies.jpashop.repository.OrderSearch;
import jskstudies.jpashop.service.ItemService;
import jskstudies.jpashop.service.MemberService;
import jskstudies.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("order")
    public String createForm(Model model){
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("order")
    public String createOrder(@RequestParam("memberId") Long memberId,
                              @RequestParam("itemId") Long itemId,
                              @RequestParam("count") int count){

        orderService.order(memberId, itemId, count);

        return "redirect:/orders";
    }

    @GetMapping("orders")
    public String list(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model){
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    @GetMapping("orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId){
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
    @GetMapping("orders/{orderId}/notCancel")
    public String notCancel(@PathVariable("orderId") Long orderId){
        orderService.notCancel(orderId);
        return "redirect:/orders";
    }
}
