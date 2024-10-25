package com.sparta.scheduleplus.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.scheduleplus.dto.ScheduleRequestDto;
import com.sparta.scheduleplus.dto.ScheduleResponseDto;
import com.sparta.scheduleplus.entity.User;
import com.sparta.scheduleplus.service.ScheduleService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleController {
	private final ScheduleService scheduleService;

	@PostMapping("/create")
	public ScheduleResponseDto createSchedule(HttpServletRequest request, @Valid @RequestBody ScheduleRequestDto dto) {
		User user = (User)request.getAttribute("user");

		return scheduleService.createSchedule(user, dto);
	}

	@GetMapping("/list")
	public List<ScheduleResponseDto> listSchedule() {
		return scheduleService.listSchedule().stream().map(ScheduleResponseDto::new).toList();
	}

	@PutMapping("/update/{id}")
	public String updateSchedule(HttpServletRequest request, @Valid ScheduleRequestDto dto,
		@PathVariable Long id) {
		User user = (User)request.getAttribute("user");
		return scheduleService.updateSchedule(user.getId(), dto, id);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteSchedule(HttpServletRequest request, @PathVariable Long id) {
		User user = (User)request.getAttribute("user");
		return scheduleService.deleteSchedule(user.getId(), id);
	}

	@GetMapping("/pageList")
	public Page<ScheduleResponseDto> pageSchedules(@RequestParam(required = false, defaultValue = "5") int page,
		@RequestParam(required = false, defaultValue = "10") int size) {
		return scheduleService.pageSchedules(page - 1, size).map(ScheduleResponseDto::new);
	}

}

