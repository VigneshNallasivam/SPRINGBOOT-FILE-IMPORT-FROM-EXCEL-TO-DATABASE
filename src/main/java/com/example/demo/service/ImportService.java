package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.extras.ExcelHelper;
import com.example.demo.model.Import;
import com.example.demo.repository.ImportRepository;

@Service
public class ImportService 
{
	@Autowired
	ImportRepository importRepository;

	public void save(MultipartFile file) 
	{
		try 
		{
			List<Import> imports = ExcelHelper.excelToDatas(file.getInputStream());
			importRepository.saveAll(imports);
		} 
		catch (IOException e) 
		{
			throw new RuntimeException("fail to store excel data: " + e.getMessage());
		}
	}

	public List<Import> getAllTutorials() 
	{
		return importRepository.findAll();
	}
}


