package Cucumber_Application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

			@SuppressWarnings("deprecation")
			public class excel_Reader
			{
		
				
					
				public  String path;
				public  FileInputStream fis = null;
				public  FileOutputStream fileOut =null;
				private XSSFWorkbook workbook = null;
				private XSSFSheet sheet = null;
				private XSSFRow row   =null;
				private XSSFCell cell = null;
				
				
			public excel_Reader(String path) {
					
					this.path=path;
					try {
						fis = new FileInputStream(path);
						workbook = new XSSFWorkbook(fis);
						sheet = workbook.getSheetAt(0);
						fis.close();
					} 
					catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}	
					// returns the row count in a sheet

					

					public int getRowCount(){
						
						sheet = workbook.getSheetAt(0);
						int number=sheet.getLastRowNum()+1;
						return number;
						}
						
					
					// returns the data from a cell
					
					
					public String getCellData(String colName,int rowNum){
						try{
							if(rowNum <=0)
								return "";
						
						
						int col_Num=-1;
						
						
						sheet = workbook.getSheetAt(0);
						row=sheet.getRow(0);
						for(int i=0;i<row.getLastCellNum();i++){
							//System.out.println(row.getCell(i).getStringCellValue().trim());
							if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
								col_Num=i;
						}
						if(col_Num==-1)
							return "";
						
						
						row = sheet.getRow(rowNum-1);
						if(row==null)
							return "";
						cell = row.getCell(col_Num);
						
						if(cell==null)
							return "";
						//System.out.println(cell.getCellType());
						if(cell.getCellType()==Cell.CELL_TYPE_STRING)
							  return cell.getStringCellValue();
						else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC || cell.getCellType()==Cell.CELL_TYPE_FORMULA ){
							  
							  String cellText  = String.valueOf(cell.getNumericCellValue());
							  if (HSSFDateUtil.isCellDateFormatted(cell)) {
						           // format in form of M/D/YY
								  double d = cell.getNumericCellValue();

								  Calendar cal =Calendar.getInstance();
								  cal.setTime(HSSFDateUtil.getJavaDate(d));
						            cellText =
						             (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
						           cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
						                      cal.get(Calendar.MONTH) + "/" + 
						                      cellText;
						           
						           //System.out.println(cellText);

						         }

							  
							  
							  return cellText;
						  }else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
						      return ""; 
						  else 
							  return String.valueOf(cell.getBooleanCellValue());
						
						}
						catch(Exception e){
							
							e.printStackTrace();
							return "row "+rowNum+" or column "+colName +" does not exist in xls";
						}
					}

				}

					
					
			