package com.dhl.dashboard.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dhl.dashboard.dto.BookingQueueDto;
import com.dhl.dashboard.dto.EventQueueDto;
import com.dhl.dashboard.dto.UserDto;
import com.dhl.dashboard.entity.CsvUserModel;
import com.dhl.dashboard.service.impl.BookingDetailsService;
import com.dhl.dashboard.service.impl.CsvUserService;
import com.dhl.dashboard.util.JwtTokenUtil;

@Controller
@CrossOrigin
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private CsvUserService userService;
	@Autowired
	private BookingDetailsService bookingDetailsService;
	@Autowired
	private Environment env;
	
	
	@Value("${booking.queryParams}")
	private String bookingParamsStr;
	
	@Value("${events.queryParams}")
	private String eventParamsStr;
	
	
	@ModelAttribute("user")
    public UserDto userRegistrationDto() {
        return new UserDto();
    }

	@PostMapping("/authenticate")
	@ResponseBody
	public String createAuthenticationToken(UserDto request, ModelMap model) throws Exception {
		authenticate(request.getEmpID(), request.getPassword());
		final UserDetails userDetails = userService.loadUserByUsername(request.getEmpID());

		final String token = jwtTokenUtil.generateToken(userDetails);
		return token;
	}

	@PostMapping("/register")
	public String registerUser(UserDto userDto, BindingResult result, ModelMap model) throws Exception {
		try {
			
			CsvUserModel obj = userService.save(userDto);
			if (obj == null) {
				return "login";
			}
			model.addAttribute("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("signupError", "something went wrong please contact system administrator!");
		}
		return "login";
	}
	
	@GetMapping({"/", "/login"})
	public String login(Model model, HttpServletRequest request) {
		Map<String, String[]> paramsMap = request.getParameterMap();
		if(paramsMap.containsKey("logout")) {
			request.getSession(false).invalidate();
		}
		return "login";
	}
	
	@GetMapping("/bookingQueue")
	public String bookingQueue(Model model) {
		 SecurityContextHolder.getContext().getAuthentication().getName();
		 String countries = env.getProperty("countries");
		 if(StringUtils.isNotEmpty(countries)) {
			 model.addAttribute("countries", countries.split("\\|"));
		 }
		
		List<String> params = new ArrayList<String>();
		String[] paramArr = bookingParamsStr.split("\\,");
		for(String param:paramArr) {
			params.add(param.split("\\-")[0]);
		}
		model.addAttribute("bookingParams", params);
		
		List<String> eventParams = new ArrayList<String>();
		String[] eventParamArr = eventParamsStr.split("\\,");
		for(String param:eventParamArr) {
			eventParams.add(param.split("\\-")[0]);
		}
		model.addAttribute("eventParams", eventParams);
		return "bookingQueue";
	}

	@GetMapping("/error")
	public String error() {
		return "error";
	}
	
	@PostMapping("/bookingQueue")
	@ResponseBody
	public List<BookingQueueDto> getBookingData( BookingQueueDto bookingQueueDto, Model model ) {
		String[] sources = bookingQueueDto.getCountry().split("\\-");
		bookingQueueDto.setCountry(sources[0].trim());
		bookingQueueDto.setDbSource(sources[1].trim());
		System.out.println("bookingQueueDto: " + bookingQueueDto);
		return bookingDetailsService.getBookingDetails(bookingQueueDto);
	}
	
	@PostMapping("/eventDetails")
	@ResponseBody
	public List<EventQueueDto> getBookingData( EventQueueDto eventQueue ) {
		System.out.println("eventQueue" + eventQueue);
		String[] sources = eventQueue.getCountry().split("\\-");
		eventQueue.setCountry(sources[0].trim());
		eventQueue.setDataSource(sources[1].trim());
		return bookingDetailsService.getEventDetails(eventQueue);
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
