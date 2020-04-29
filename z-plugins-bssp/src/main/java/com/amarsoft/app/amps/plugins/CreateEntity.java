package com.amarsoft.app.amps.plugins;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import com.amarsoft.amps.dct.excel.ExcelEntity;
import com.amarsoft.amps.dct.excel.common.config.AutoCreateConstants;
import com.amarsoft.amps.dct.excel.common.utils.AutoCreateHelper;
import com.amarsoft.amps.dct.excel.entity.pojo.EntityRelation;

public class CreateEntity {
	public static final int[] TABLE_DESCRIBE_INDEX = new int[]{1, 0};
	public static final int[] ENTITYNAME_INDEX = new int[]{1, 2};
	public static final int[] TABLENAME_INDEX = new int[]{1, 4};
	public static final int[] ISHASHISTORY_INDEX = new int[]{1, 5};
	public static final int[] HISTORY_ENTITY_INDEX = new int[]{1, 6};
	public static final int[] HISTORY_TABLENAME_INDEX = new int[]{1, 7};
	public static final int[] AUTO_CREATEKEY_INDEX = new int[]{1, 8};
	public static final int[] KEY_CACHE_NUM_INDEX = new int[]{1, 10};
	public static final int[] ISCLEAR_INDEX = new int[]{1, 11};
	public static final int[] SERVERNAME_INDEX = new int[]{1, 15};

	public static void create(ExcelEntity[] entitys) throws Exception {
		ExcelEntity[] var1 = entitys;
		int var2 = entitys.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			ExcelEntity entity = var1[var3];
			InputStream is = null;
			XSSFWorkbook xssfWorkbook = null;

			try {
				is = new FileInputStream(AutoCreateConstants.RESOURCE_PATH + entity.excelName);
				xssfWorkbook = new XSSFWorkbook(is);

				for (int i = entity.beginSheetIndex; i < xssfWorkbook.getNumberOfSheets(); ++i) {
					XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(i);
					int[] indexStartEnd = new int[]{0, 0};
					int[] columnStartEnd = new int[]{0, 0};
					int[] relationStartEnd = new int[]{0, 0};
					String tableDescribe = AutoCreateHelper.getCellString(xssfSheet, TABLE_DESCRIBE_INDEX);
					String entityName = AutoCreateHelper.getCellString(xssfSheet, ENTITYNAME_INDEX);
					String tableName = AutoCreateHelper.getCellString(xssfSheet, TABLENAME_INDEX);
					String isHasHistory = AutoCreateHelper.getCellString(xssfSheet, ISHASHISTORY_INDEX);
					String historyTableName = AutoCreateHelper.getCellString(xssfSheet, HISTORY_TABLENAME_INDEX);
					String historyEntity = AutoCreateHelper.getCellString(xssfSheet, HISTORY_ENTITY_INDEX);
					String isAutoCreateKey = AutoCreateHelper.getCellString(xssfSheet, AUTO_CREATEKEY_INDEX);
					if (StringUtils.isEmpty(AutoCreateHelper.getCellString(xssfSheet, KEY_CACHE_NUM_INDEX))) {
						Object var10000 = null;
					} else {
						Integer.parseInt(AutoCreateHelper.getCellString(xssfSheet, KEY_CACHE_NUM_INDEX));
					}

					String isClear = AutoCreateHelper.getCellString(xssfSheet, ISCLEAR_INDEX);
					String serverName = AutoCreateHelper.getCellString(xssfSheet, SERVERNAME_INDEX);
					File file = new File(AutoCreateConstants.BASE_PATH + "ems-" + serverName);
					if (!file.exists() || !file.isDirectory()) {
						throw new Exception("AutoCreateEntity.ExcelConfig配置不正确！");
					}

					try {
						StringBuffer stringBuffer = new StringBuffer();
						StringBuffer stringBufferBase = new StringBuffer();
						StringBuffer stringBufferColumns = new StringBuffer();
						StringBuffer stringBufferGetSet = new StringBuffer();
						StringBuffer stringBufferHistory = new StringBuffer();
						stringBuffer.append("" + System.getProperty("line.separator", "\n"));
						stringBuffer.append("package " + entity.applicationPackage + serverName + ".entity;"
								+ System.getProperty("line.separator", "\n")
								+ System.getProperty("line.separator", "\n"));
						stringBuffer.append("import com.amarsoft.amps.arem.annotation.Description;"
								+ System.getProperty("line.separator", "\n"));
						stringBuffer.append("import com.amarsoft.amps.arpe.annotation.EntityRelationShip;"
								+ System.getProperty("line.separator", "\n"));
						stringBuffer.append("import com.amarsoft.amps.arpe.annotation.GeneratedKey;"
								+ System.getProperty("line.separator", "\n")
								+ System.getProperty("line.separator", "\n"));
						stringBuffer.append("import javax.persistence.*;" + System.getProperty("line.separator", "\n"));
						stringBuffer.append("import com.amarsoft.amps.arem.annotation.Description;"
								+ System.getProperty("line.separator", "\n"));
						stringBuffer.append("import com.amarsoft.amps.arpe.businessobject.BusinessObject;"
								+ System.getProperty("line.separator", "\n")
								+ System.getProperty("line.separator", "\n"));
						stringBuffer.append("import javax.persistence.*;" + System.getProperty("line.separator", "\n"));
						stringBuffer
								.append("import java.math.BigDecimal;" + System.getProperty("line.separator", "\n"));
						stringBuffer.append("import lombok.Getter;" + System.getProperty("line.separator", "\n"));
						stringBuffer.append("import lombok.Setter;" + System.getProperty("line.separator", "\n"));
						stringBuffer.append("" + System.getProperty("line.separator", "\n"));
						stringBuffer.append("@Getter" + System.getProperty("line.separator", "\n"));
						stringBuffer.append("@Setter" + System.getProperty("line.separator", "\n"));
						stringBuffer.append(
								"@Description(\"" + tableDescribe + "\")" + System.getProperty("line.separator", "\n"));
						stringBuffer.append("@Entity" + System.getProperty("line.separator", "\n"));
						stringBuffer.append("@Table(" + System.getProperty("line.separator", "\n"));
						stringBuffer.append("    name=\"" + tableName.toUpperCase() + "\"");
						int k = 2;

						while (true) {
							XSSFRow xssfRow = xssfSheet.getRow(k);
							String length = xssfRow.getCell(0).toString();
							if ("索引".equals(length.trim())) {
								indexStartEnd[0] = k + 2;
							} else if ("关联条件".equals(length.trim())) {
								indexStartEnd[1] = k - 1;
								relationStartEnd[0] = k + 2;
							} else if ("属性".equals(length.trim())) {
								relationStartEnd[1] = k - 1;
								columnStartEnd[0] = k + 2;
							} else if ("endline".equals(length.trim())) {
								columnStartEnd[1] = k - 1;
								if (indexStartEnd[1] - indexStartEnd[0] >= 0) {
									stringBuffer.append("," + System.getProperty("line.separator", "\n"));
									stringBuffer.append("    indexes = {" + System.getProperty("line.separator", "\n"));

									for (k = indexStartEnd[0]; k <= indexStartEnd[1]; ++k) {
										xssfRow = xssfSheet.getRow(k);
										if (xssfRow.getCell(0).toString() != "") {
											stringBuffer.append("       @Index(name=\"" + xssfRow.getCell(0)
													+ "\",columnList=\"" + xssfRow.getCell(1) + "\"),"
													+ System.getProperty("line.separator", "\n"));
										}
									}

									stringBuffer.append("    }" + System.getProperty("line.separator", "\n"));
								}

								stringBuffer.append(")" + System.getProperty("line.separator", "\n"));
								stringBuffer.append("@GeneratedKey(autoGenerateKey=true, allocationSize=1000)"
										+ System.getProperty("line.separator", "\n"));
								String clolumn;
								if (relationStartEnd[1] - relationStartEnd[0] >= 0) {
									Map<String, EntityRelation> relationMap = new HashMap();

									for (k = relationStartEnd[0];k<= relationStartEnd[1]; ++k) {
										xssfRow = xssfSheet.getRow(k);
										if (xssfRow.getCell(0).toString() != "") {
											clolumn = xssfRow.getCell(0).toString();
											EntityRelation entityRelation;
											if (relationMap.get(clolumn) != null) {
												entityRelation = (EntityRelation) relationMap.get(clolumn);
												entityRelation.setColumn(entityRelation.getColumn() + ",\""
														+ xssfRow.getCell(1).toString() + "\"");
												entityRelation.setRelativeColumn(entityRelation.getRelativeColumn()
														+ ",\"" + xssfRow.getCell(2).toString() + "\"");
												relationMap.put(clolumn, entityRelation);
											} else {
												entityRelation = new EntityRelation();
												entityRelation.setColumn("\"" + xssfRow.getCell(1).toString() + "\"");
												entityRelation
														.setRelativeColumn("\"" + xssfRow.getCell(2).toString() + "\"");
												relationMap.put(clolumn, entityRelation);
											}
										}
									}

									Iterator var47 = relationMap.entrySet().iterator();

									while (var47.hasNext()) {
										Entry<String, EntityRelation> entry = (Entry) var47.next();
										EntityRelation entityRelation = (EntityRelation) entry.getValue();
										stringBuffer
												.append("@EntityRelationShip(foreignEntity = " + (String) entry.getKey()
														+ ".class, columns = {" + entityRelation.getColumn()
														+ "}, foreignColumns = {" + entityRelation.getRelativeColumn()
														+ "})" + System.getProperty("line.separator", "\n"));
									}
								}

								if (!"是".equals(isHasHistory)) {
									stringBuffer.append("public class " + entityName + " extends BusinessObject {"
											+ System.getProperty("line.separator", "\n"));
									stringBuffer.append("    private static final long serialVersionUID = 1L;"
											+ System.getProperty("line.separator", "\n"));
								} else {
									stringBufferHistory.append("" + System.getProperty("line.separator", "\n"));
									stringBufferHistory.append("package " + entity.applicationPackage + serverName
											+ ".entity;" + System.getProperty("line.separator", "\n")
											+ System.getProperty("line.separator", "\n"));
									stringBufferHistory.append("import com.amarsoft.amps.arem.annotation.Description;"
											+ System.getProperty("line.separator", "\n"));
									stringBufferHistory
											.append("import com.amarsoft.amps.arpe.annotation.EntityRelationShip;"
													+ System.getProperty("line.separator", "\n"));
									stringBufferHistory.append("import com.amarsoft.amps.arpe.annotation.GeneratedKey;"
											+ System.getProperty("line.separator", "\n")
											+ System.getProperty("line.separator", "\n"));
									stringBufferHistory.append(
											"import javax.persistence.*;" + System.getProperty("line.separator", "\n"));
									stringBufferHistory.append("import com.amarsoft.amps.arem.annotation.Description;"
											+ System.getProperty("line.separator", "\n"));
									stringBufferHistory
											.append("import com.amarsoft.amps.arpe.businessobject.BusinessObject;"
													+ System.getProperty("line.separator", "\n")
													+ System.getProperty("line.separator", "\n"));
									stringBufferHistory.append(
											"import javax.persistence.*;" + System.getProperty("line.separator", "\n"));
									stringBufferHistory.append("import java.math.BigDecimal;"
											+ System.getProperty("line.separator", "\n"));
									stringBufferHistory.append(
											"import lombok.Getter;" + System.getProperty("line.separator", "\n"));
									stringBufferHistory.append(
											"import lombok.Setter;" + System.getProperty("line.separator", "\n"));
									stringBufferHistory.append("" + System.getProperty("line.separator", "\n"));
									stringBufferHistory.append("@Getter" + System.getProperty("line.separator", "\n"));
									stringBufferHistory.append("@Setter" + System.getProperty("line.separator", "\n"));
									stringBufferHistory.append("@Description(\"" + tableDescribe + "\")"
											+ System.getProperty("line.separator", "\n"));
									stringBufferHistory.append("@Entity" + System.getProperty("line.separator", "\n"));
									stringBufferHistory.append("@Table(" + System.getProperty("line.separator", "\n"));
									stringBufferHistory.append("    name=\"" + historyTableName.toUpperCase() + "\"");
									if (indexStartEnd[1] - indexStartEnd[0] >= 0) {
										stringBufferHistory.append("," + System.getProperty("line.separator", "\n"));
										stringBufferHistory
												.append("    indexes = {" + System.getProperty("line.separator", "\n"));

										for (k = indexStartEnd[0]; k <= indexStartEnd[1]; ++k) {
											xssfRow = xssfSheet.getRow(k);
											if (xssfRow.getCell(0).toString() != "") {
												stringBufferHistory.append("       @Index(name=\""
														+ xssfRow.getCell(0).toString().replace(tableName,
																historyTableName)
														+ "\",columnList=\"" + xssfRow.getCell(1) + "\"),"
														+ System.getProperty("line.separator", "\n"));
											}
										}

										stringBufferHistory
												.append("    }" + System.getProperty("line.separator", "\n"));
									}

									stringBufferHistory.append(")" + System.getProperty("line.separator", "\n"));
									stringBufferHistory.append("public class " + historyEntity + " extends "
											+ entityName + "Base {" + System.getProperty("line.separator", "\n"));
									stringBufferHistory.append("    private static final long serialVersionUID = 1L;"
											+ System.getProperty("line.separator", "\n"));
									stringBuffer.append("public class " + entityName + " extends " + entityName
											+ "Base {" + System.getProperty("line.separator", "\n"));
									stringBuffer.append("    private static final long serialVersionUID = 1L;"
											+ System.getProperty("line.separator", "\n"));
								}

								stringBufferBase.append("package " + entity.applicationPackage + serverName + ".entity;"
										+ System.getProperty("line.separator", "\n")
										+ System.getProperty("line.separator", "\n"));
								stringBufferBase.append("import com.amarsoft.amps.arem.annotation.Description;"
										+ System.getProperty("line.separator", "\n"));
								stringBufferBase.append("import com.amarsoft.amps.arpe.businessobject.BusinessObject;"
										+ System.getProperty("line.separator", "\n")
										+ System.getProperty("line.separator", "\n"));
								stringBufferBase.append(
										"import javax.persistence.*;" + System.getProperty("line.separator", "\n"));
								stringBufferBase.append(
										"import java.math.BigDecimal;" + System.getProperty("line.separator", "\n"));
								stringBufferBase
										.append("import lombok.Getter;" + System.getProperty("line.separator", "\n"));
								stringBufferBase
										.append("import lombok.Setter;" + System.getProperty("line.separator", "\n"));
								stringBufferBase.append("@Getter" + System.getProperty("line.separator", "\n"));
								stringBufferBase.append("@Setter" + System.getProperty("line.separator", "\n"));
								stringBufferBase
										.append("@MappedSuperclass" + System.getProperty("line.separator", "\n"));
								stringBufferBase.append("@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)"
										+ System.getProperty("line.separator", "\n"));
								stringBufferBase.append("public class " + entityName + "Base extends BusinessObject {"
										+ System.getProperty("line.separator", "\n"));
								stringBufferBase.append("    private static final long serialVersionUID = 1L;"
										+ System.getProperty("line.separator", "\n"));

								String column;
								String[] var34;
								int var35;
								int var36;
								String col;
								String[] columns;
								for (k = columnStartEnd[0]; k <= columnStartEnd[1]; ++k) {
									xssfRow = xssfSheet.getRow(k);
									length = xssfRow.getCell(3).toString();
									if (length.indexOf(".") > 0) {
										length = length.substring(0, length.indexOf("."));
									}

									clolumn = xssfRow.getCell(0).toString();
									if (clolumn.indexOf("_") > 0) {
										columns = clolumn.split("_");
										column = "";
										var34 = columns;
										var35 = columns.length;

										for (var36 = 0; var36 < var35; ++var36) {
											col = var34[var36];
											if ("".equals(column)) {
												column = col;
											} else {
												column = column + col.substring(0, 1).toUpperCase()
														+ col.substring(1, col.length());
											}
										}

										clolumn = column;
									}

									stringBufferColumns.append("      " + System.getProperty("line.separator", "\n"));
									stringBufferColumns.append("    @Description(\"" + xssfRow.getCell(1) + "\") "
											+ System.getProperty("line.separator", "\n"));
									if ("是".equals(xssfRow.getCell(4).getStringCellValue())) {
										stringBufferColumns
												.append("    @Id " + System.getProperty("line.separator", "\n"));
										stringBufferColumns.append("    @Column(name = \"" + xssfRow.getCell(0)
												+ "\", nullable=false,length=" + length + ") "
												+ System.getProperty("line.separator", "\n"));
									} else if ("否".equals(xssfRow.getCell(6).getStringCellValue())
											&& "String".equals(xssfRow.getCell(2).getStringCellValue())) {
										stringBufferColumns.append("    @Column(name = \"" + xssfRow.getCell(0)
												+ "\", nullable=false,length=" + length + ") "
												+ System.getProperty("line.separator", "\n"));
									} else if ("BigDecimal".equals(xssfRow.getCell(2).getStringCellValue())) {
										stringBufferColumns.append("    @Column(name = \"" + xssfRow.getCell(0)
												+ "\", precision="
												+ xssfRow.getCell(3).getStringCellValue().split(",")[0] + ", scale="
												+ xssfRow.getCell(3).getStringCellValue().split(",")[1] + ") "
												+ System.getProperty("line.separator", "\n"));
									} else if ("Integer".equals(xssfRow.getCell(2).getStringCellValue())) {
										stringBufferColumns.append("    @Column(name = \"" + xssfRow.getCell(0) + "\") "
												+ System.getProperty("line.separator", "\n"));
									} else {
										stringBufferColumns
												.append("    @Column(name = \"" + xssfRow.getCell(0) + "\",length="
														+ length + ") " + System.getProperty("line.separator", "\n"));
									}

									stringBufferColumns.append("    private " + xssfRow.getCell(2).getStringCellValue()
											+ " " + clolumn + ";" + System.getProperty("line.separator", "\n"));
								}

								for (k = columnStartEnd[0]; k <= columnStartEnd[1]; ++k) {
									xssfRow = xssfSheet.getRow(k);
									length = xssfRow.getCell(3).toString();
									if (length.indexOf(".") > 0) {
										length = length.substring(0, length.indexOf("."));
									}

									if ("BigDecimal".equals(xssfRow.getCell(2).getStringCellValue())) {
										stringBufferGetSet.append("     " + System.getProperty("line.separator", "\n"));
										clolumn = xssfRow.getCell(0).toString();
										if (clolumn.indexOf("_") > 0) {
											columns = clolumn.split("_");
											column = "";
											var34 = columns;
											var35 = columns.length;

											for (var36 = 0; var36 < var35; ++var36) {
												col = var34[var36];
												if ("".equals(column)) {
													column = col;
												} else {
													column = column + col.substring(0, 1).toUpperCase()
															+ col.substring(1, col.length());
												}
											}

											clolumn = column;
										}

										String clolumnUp = clolumn.substring(0, 1).toUpperCase()
												+ clolumn.substring(1, clolumn.length());
										stringBufferGetSet.append("    public double getD" + clolumnUp + "() {"
												+ System.getProperty("line.separator", "\n"));
										stringBufferGetSet.append("        return " + clolumn + " == null ? 0.00 : "
												+ clolumn + ".doubleValue();"
												+ System.getProperty("line.separator", "\n"));
										stringBufferGetSet.append("    }" + System.getProperty("line.separator", "\n"));
										stringBufferGetSet.append("     " + System.getProperty("line.separator", "\n"));
										stringBufferGetSet.append("    public void setD" + clolumnUp + "(double "
												+ clolumn + ") {" + System.getProperty("line.separator", "\n"));
										stringBufferGetSet.append("        this." + clolumn + " = BigDecimal.valueOf("
												+ clolumn + ");" + System.getProperty("line.separator", "\n"));
										stringBufferGetSet.append("    }" + System.getProperty("line.separator", "\n"));
									}
								}

								if ("是".equals(isHasHistory)) {
									stringBufferBase.append(stringBufferColumns);
									stringBufferBase.append(stringBufferGetSet);
								} else {
									stringBuffer.append(stringBufferColumns);
									stringBuffer.append(stringBufferGetSet);
								}

								stringBufferBase.append("}" + System.getProperty("line.separator", "\n"));
								stringBuffer.append("}" + System.getProperty("line.separator", "\n"));
								stringBufferHistory.append("}" + System.getProperty("line.separator", "\n"));
								System.out.println(stringBuffer);
								if ("是".equals(isHasHistory)) {
									AutoCreateHelper.createFile(stringBuffer, serverName,
											entity.applicationPackage.replaceAll("\\.", "/") + serverName + "/entity",
											entityName, false);
									AutoCreateHelper.createFile(stringBufferBase, serverName,
											entity.applicationPackage.replaceAll("\\.", "/") + serverName + "/entity",
											entityName + "Base", false);
									AutoCreateHelper.createFile(stringBufferHistory, serverName,
											entity.applicationPackage.replaceAll("\\.", "/") + serverName + "/entity",
											historyEntity, false);
								} else {
									AutoCreateHelper.createFile(stringBuffer, "ems-"+serverName,
											entity.applicationPackage.replaceAll("\\.", "/") + serverName + "/entity",
											entityName, false);
								}
								break;
							}

							++k;
						}
					} catch (Exception var42) {
						xssfWorkbook.close();
						throw new Exception(
								"[" + i + "]-" + entity.excelName + "-" + xssfSheet.getSheetName() + "-DTO-数据解析异常！",
								var42);
					}
				}
			} catch (Exception var43) {
				throw new Exception(entity.excelName + "处理异常！", var43);
			} finally {
				if (xssfWorkbook != null) {
					xssfWorkbook.close();
				}

				if (is != null) {
					is.close();
				}

			}
		}

	}
}