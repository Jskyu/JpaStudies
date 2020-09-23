package jpastudyt4.jskshop.controller;

import jpastudyt4.jskshop.api.queryService.OrderQueryService;
import jpastudyt4.jskshop.domain.Member;
import jpastudyt4.jskshop.domain.Order;
import jpastudyt4.jskshop.domain.items.Item;
import jpastudyt4.jskshop.service.ItemService;
import jpastudyt4.jskshop.service.MemberService;
import jpastudyt4.jskshop.service.OrderSearch;
import jpastudyt4.jskshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ItemService itemService;
    private final MemberService memberService;
    private final OrderQueryService orderQueryService;

    @GetMapping("/order")
    public String createForm(Model model){

        List<Member> members = memberService.findMember();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,
                              @RequestParam("itemId") Long itemId,
                              @RequestParam("count") int count){
        orderService.order(memberId, itemId, count);
        return "redirect:/";
    }

    @GetMapping("/orders")
    public String list(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model){
        List<OrderControllerDto> orders = orderQueryService.list(orderSearch);
        model.addAttribute("orders", orders);
        return "order/orderListTest";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long id){
        orderService.cancelOrder(id);
        return "redirect:/orders";
    }

    @PostMapping("/orders/{orderId}/notCancel")
    public String notCancel(@PathVariable("orderId") Long id){
        orderService.notCancel(id);
        return "redirect:/orders";
    }



}
