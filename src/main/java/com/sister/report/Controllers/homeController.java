package com.sister.report.Controllers;

import com.sister.report.Models.*;

import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.buf.Utf8Encoder;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.web.bind.annotation.*;

@RestController
public class homeController {
	
	@GetMapping("/")
	public String index() {
		return "welcome";
	}
	
	@GetMapping("/excel")
	@CrossOrigin("*")
	public void GetExcel(HttpServletResponse response) {
		String fileName = "猜猜我是誰.xlsx";
		XSSFWorkbook wb = new XSSFWorkbook();
		wb.createSheet("測試");
		XSSFSheet sh1 = wb.getSheet("測試");
		Customer initialCustomer = this.InitialData();
		Produce pd = initialCustomer.getProduce();
		XSSFRow aa = sh1.createRow(0);
		
		
		Cell cell0 = aa.createCell(0);
		cell0.setCellValue(initialCustomer.getCustomerName());

		Cell cell1 = aa.createCell(1);
		cell1.setCellValue(pd.getDataSource());
		Cell cell2 = aa.createCell(2);
		cell2.setCellValue(pd.getName());
		Cell cell3 = aa.createCell(3);
		cell3.setCellValue(pd.getPrice());
		Cell cell4 = aa.createCell(4);
		cell4.setCellValue(pd.getQuantity());
		Cell cell5 = aa.createCell(5);
		cell5.setCellValue(pd.getSales());
		Cell cell6 = aa.createCell(6);
		cell6.setCellValue(pd.getMemo());
		try {
			this.output(fileName, wb, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void output(String fileName, Workbook wb, HttpServletResponse response) throws IOException {
		// 網站下載Excel
		String fileNameEncode = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name());
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		response.setContentType("application/x-msdownload; charset=UTF-8");
		response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
		response.setHeader("Content-disposition", "attachment; filename=" + fileNameEncode);
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			wb.write(out);
		} catch (Exception e) {
			// TODO: handle exception
			throw(e);
		} finally {
			if(out != null) {
				out.flush();
				out.close();
			}
		}
	}
	
	private Customer InitialData() {
		Customer cs = new Customer();
		Produce pd = new Produce();
		pd.setDataSource("shopee");
		pd.setName("束帶工具");
		pd.setMemo("缺貨");
		pd.setPrice(150);
		pd.setQuantity(3);
		pd.setSales(768);
		cs.setProduce(pd);
		cs.setCustomerName("我猜猜");
		return cs;
	}
}
