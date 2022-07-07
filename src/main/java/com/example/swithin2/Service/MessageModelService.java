package com.example.swithin2.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.swithin2.model.MessageModel;
import com.example.swithin2.model.MessageModelRepository;

@Service
public class MessageModelService {

	@Autowired
	private MessageModelRepository messageModelRepository;

	public void addMessage(MessageModel message) {

		messageModelRepository.save(message);

	}
}
