package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.extras.ExcelHelper;
import com.example.demo.model.Import;
import com.example.demo.response.Message;
import com.example.demo.service.ImportService;

@RestController
@RequestMapping("/import")
public class ImportController 
{
	@Autowired
	ImportService importService;

	@PostMapping("/upload")
	public ResponseEntity<Message> uploadFile(@RequestParam("file") MultipartFile file) 
	{
		String message = "";

		if (ExcelHelper.hasExcelFormat(file)) 
		{
			try 
			{
				importService.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new Message(message));
			} 
			catch (Exception e) 
			{
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new Message(message));
			}
		}

		message = "Please upload an excel file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message(message));
	}

	@GetMapping("/imports")
	public ResponseEntity<List<Import>> getAllTutorials() 
	{
		try 
		{
			List<Import> tutorials = importService.getAllTutorials();
            if (tutorials.isEmpty()) 
            {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} 
		catch (Exception e) 
		{
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}


