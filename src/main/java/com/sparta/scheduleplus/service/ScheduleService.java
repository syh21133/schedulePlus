package com.sparta.scheduleplus.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sparta.scheduleplus.dto.ScheduleRequestDto;
import com.sparta.scheduleplus.dto.ScheduleResponseDto;
import com.sparta.scheduleplus.entity.Schedule;
import com.sparta.scheduleplus.entity.User;
import com.sparta.scheduleplus.repository.ScheduleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleService {

	private final ScheduleRepository scheduleRepository;
	private final UserService userService;

	// public ScheduleResponseDto createSchedule(Long userId, ScheduleRequestDto dto) {
	//     User user = userService.findUser(userId);
	//
	//     Schedule schedule = new Schedule(dto,user);
	//     Schedule savedSchedule = scheduleRepository.save(schedule);
	//     ScheduleResponseDto responseDto = new ScheduleResponseDto(savedSchedule);
	//
	//     return responseDto;
	// }
	public ScheduleResponseDto createSchedule(User user, ScheduleRequestDto dto) {
		Schedule schedule = new Schedule(dto, user);
		Schedule savedSchedule = scheduleRepository.save(schedule);
		ScheduleResponseDto responseDto = new ScheduleResponseDto(savedSchedule);

		return responseDto;

	}

	public List<Schedule> listSchedule() {
		return scheduleRepository.findAllByOrderByModifiedAtDesc();
	}

	public String updateSchedule(Long userId, ScheduleRequestDto dto, Long id) {
		User user = userService.findUser(userId);

		Schedule schedule = findById(id);

		if (!user.equals(schedule.getUser()))
			return "일치하지 않습니다.";

		schedule.setTitle(dto.getTitle());
		schedule.setTodo(dto.getTodo());
		Schedule savedSchedule = scheduleRepository.save(schedule);
		ScheduleResponseDto responseDto = new ScheduleResponseDto(savedSchedule);
		return "수정";
	}

	public String deleteSchedule(Long userId, Long id) {
		User user = userService.findUser(userId);

		Schedule schedule = findById(id);

		if (!user.equals(schedule.getUser()))
			return "일치하지 않습니다.";

		scheduleRepository.deleteById(id);

		return "삭제";
	}

	private Schedule findById(Long id) {
		return scheduleRepository.findById(id).orElseThrow(() ->
			new RuntimeException("Schedule not found"));
	}

	public Page<Schedule> pageSchedules(int i, int size) {

		Pageable pageable = PageRequest.of(i, size);
		return scheduleRepository.findAll(pageable);

	}

}
