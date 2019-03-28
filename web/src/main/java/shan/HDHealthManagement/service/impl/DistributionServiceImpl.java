package shan.HDHealthManagement.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import shan.HDHealthManagement.Mapper.DistributionDao;
import shan.HDHealthManagement.po.Distribution;
import shan.HDHealthManagement.service.DistributionService;

@Service(value="distributionService")
@Transactional
public class DistributionServiceImpl implements DistributionService {
	@Resource
	private DistributionDao distributionDao;
	
	public Boolean add(CommonsMultipartFile file) {
		 try {
			distributionDao.del();
			Workbook workbook = WorkbookFactory.create(file.getInputStream());
			Sheet sheet = workbook.getSheetAt(0);
			int rows = sheet.getLastRowNum() + 1;
			for (int i = 3;i<rows;i++) {
				Row row = sheet.getRow(i);
				Distribution distribution = new Distribution();
				Cell cell = row.getCell(1);
				if("".equals(cell.getStringCellValue())){
					continue;
				}
				distribution.setDistribution(row.getCell(1).getStringCellValue());
				distribution.setNum(Double.valueOf(row.getCell(2).getNumericCellValue()).intValue());
				distribution.setMemo(row.getCell(3).getStringCellValue());
				distribution.setFlag(1);
				distributionDao.add(distribution);
			}
			sheet = workbook.getSheetAt(1);
			rows = sheet.getLastRowNum() + 1;
			for (int i = 2;i<rows;i++) {
				Row row = sheet.getRow(i);
				Distribution distribution = new Distribution();
				Cell cell = row.getCell(1);
				if("".equals(cell.getStringCellValue())){
					continue;
				}
				distribution.setDistribution(row.getCell(1).getStringCellValue());
				distribution.setNum(Double.valueOf(row.getCell(2).getNumericCellValue()).intValue());
				distribution.setMemo(row.getCell(3).getStringCellValue());
				distribution.setFlag(2);
				distributionDao.add(distribution);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return false;
	}
	
	@Transactional(readOnly  = true)
	public List<Distribution> findByRegional() {
		return distributionDao.findByRegional();
	}

	@Transactional(readOnly  = true)
	public List<Distribution> findByAge() {
		return distributionDao.findByAge();
	}

	@Transactional(readOnly  = true)
	public List<Distribution> getAll() {
		return distributionDao.getAll();
	}

}
