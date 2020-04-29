package com.amarsoft.app.amps.plugins;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import com.amarsoft.amps.dct.excel.ExcelTemplate;
import com.amarsoft.amps.dct.excel.serviceandtemplate.config.TemplateConstants;
import com.amarsoft.amps.dct.excel.serviceandtemplate.generator.template.ClientGenerator;
import com.amarsoft.amps.dct.excel.serviceandtemplate.generator.template.ControllerGenerator;
import com.amarsoft.amps.dct.excel.serviceandtemplate.generator.template.ControllerImplGenerator;
import com.amarsoft.amps.dct.excel.serviceandtemplate.generator.template.DtoGenerator;
import com.amarsoft.amps.dct.excel.serviceandtemplate.generator.template.ServiceGenerator;
import com.amarsoft.amps.dct.excel.serviceandtemplate.generator.template.ServiceImplGenerator;
import com.amarsoft.amps.dct.excel.serviceandtemplate.pojo.template.ServerConfig;
import com.amarsoft.amps.dct.excel.serviceandtemplate.pojo.template.TemplateConfig;
import com.amarsoft.amps.dct.excel.serviceandtemplate.utils.AutoCreateHelper;

public class CreateTemplateCoder {
	public static Map<String, ServerConfig> serverConfigs = new LinkedHashMap();
	public static List<String> excludeFilePaths = new LinkedList();
	public static List<String> excludeDtoPaths = new LinkedList();

	public static void create(ExcelTemplate[] templates) throws Exception {
		ExcelTemplate[] var1 = templates;
		int var2 = templates.length;

		label206 : for (int var3 = 0; var3 < var2; ++var3) {
			ExcelTemplate template = var1[var3];

			try {
				ServerConfig serverConfig = (ServerConfig) serverConfigs.get(template.serverId);
				if (serverConfig == null) {
					serverConfig = new ServerConfig();
					serverConfig.setServerId(template.serverId);
					serverConfig.setDistributed(template.isDistributed);
					serverConfig
							.setDtoBasePackage(template.applicationPackage + template.serverId + ".template.cs.dto");
					serverConfig.setControllerBasePackage(
							template.applicationPackage + template.serverId + ".template.controller");
					serverConfig.setControllerImplBasePackage(
							template.applicationPackage + template.serverId + ".template.controller.impl");
					serverConfig.setClientBasePackage(
							template.applicationPackage + template.serverId + ".template.cs.client");
					serverConfig.setServiceBasePackage(
							template.applicationPackage + template.serverId + ".template.service");
					serverConfig.setServiceImplBasePackage(
							template.applicationPackage + template.serverId + ".template.service.impl");
					serverConfig.setDtoBaseFolderPath(TemplateConstants.BASE_PATH + "ems-" +template.serverId + "-cs"
							+ "/src/main/java/" + serverConfig.getDtoBasePackage().replaceAll("\\.", "/") + "/");
					serverConfig.setControllerBaseFolderPath(TemplateConstants.BASE_PATH + "ems-" +template.serverId + "-cs"
							+ "/src/main/java/" + serverConfig.getControllerBasePackage().replaceAll("\\.", "/") + "/");
					serverConfig.setControllerImplBaseFolderPath(
							TemplateConstants.BASE_PATH + "ems-" +template.serverId + "/src/main/java/"
									+ serverConfig.getControllerImplBasePackage().replaceAll("\\.", "/") + "/");
					serverConfig.setClientBaseFolderPath(TemplateConstants.BASE_PATH + "ems-" +template.serverId + "-cs"
							+ "/src/main/java/" + serverConfig.getClientBasePackage().replaceAll("\\.", "/") + "/");
					serverConfig.setServiceBaseFolderPath(TemplateConstants.BASE_PATH + "ems-" +template.serverId
							+ "/src/main/java/" + serverConfig.getServiceBasePackage().replaceAll("\\.", "/") + "/");
					serverConfig.setServiceImplBaseFolderPath(
							TemplateConstants.BASE_PATH + "ems-" +template.serverId + "/src/main/java/"
									+ serverConfig.getServiceImplBasePackage().replaceAll("\\.", "/") + "/");
					serverConfigs.put(template.serverId, serverConfig);
				}

				if (StringUtils.isEmpty(TemplateConstants.BASE_PATH)) {
					throw new Exception("请设置TemplateConstants.BASE_PATH的值！");
				}

				String serverPath = TemplateConstants.BASE_PATH + "ems-"+template.serverId;
				File file = new File(serverPath);
				if (file.exists() && file.isDirectory()) {
					InputStream is = new FileInputStream(TemplateConstants.RESOURCE_PATH + template.excelName);
					XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);

					TemplateConfig templateConfig;
					int t = 0;
					int p = 0;
					Map<String,Object> flag = new HashMap<>();
					for (int i = template.beginSheetIndex; i < xssfWorkbook.getNumberOfSheets(); ++i) {
						XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(i);
						try {
							if (serverConfig.getTemplateConfigs().containsKey(
									AutoCreateHelper.getCellString(xssfSheet, TemplateConstants.TEMPLATE_ID_INDEX))) {
								throw new Exception(serverConfig.getServerId() + "重复的模板编号：" + AutoCreateHelper
										.getCellString(xssfSheet, TemplateConstants.TEMPLATE_ID_INDEX));
							}

							templateConfig = new TemplateConfig(serverConfig, xssfSheet, i,
									template.applicationPackage);
							// 标识为是否首次生成：true代表只首次生成，flase代表不是首次生成
							//flag.put(templateConfig.getTemplate().getTemplateId(), false);
							serverConfig.getTemplateConfigs().put(templateConfig.getTemplate().getTemplateId(),
									templateConfig);
							/*if("重新生成".equals(AutoCreateHelper.getCellString(xssfSheet, TemplateConstants.GENERATE_TYPE_DTO_INDEX))) {
								excludeDtoPaths.add(t, serverConfig.getDtoBaseFolderPath());
								t++;
							}
							if("重新生成".equals(AutoCreateHelper.getCellString(xssfSheet, TemplateConstants.GENERATE_TYPE_SERVICE_INDEX))) {
								excludeFilePaths.add(p, serverConfig.getControllerBaseFolderPath());
								p++;
								excludeFilePaths.add(p, serverConfig.getClientBaseFolderPath());
								p++;
								excludeFilePaths.add(p, serverConfig.getControllerImplBaseFolderPath());
								p++;
							}
							if("重新生成".equals(AutoCreateHelper.getCellString(xssfSheet, TemplateConstants.GENERATE_TYPE_CONTROLLER_INDEX))) {
								excludeFilePaths.add(p, serverConfig.getServiceBaseFolderPath());
								p++;
								excludeFilePaths.add(p, serverConfig.getServiceImplBaseFolderPath());
								p++;
							}*/
							
						} catch (Exception var21) {
							throw new Exception(
									"[" + i + "]-" + template.excelName + "-" + xssfSheet.getSheetName() + "-数据解析异常！",
									var21);
						} finally {
							if (xssfWorkbook != null) {
								xssfWorkbook.close();
							}

							if (is != null) {
								is.close();
							}

						}
					}
					
					// 代码清理
					try {
						Iterator var24 = serverConfigs.keySet().iterator();

						while (var24.hasNext()) {
							String key = (String) var24.next();
							ServerConfig serverConfig1 = (ServerConfig) serverConfigs.get(key);
							AutoCreateHelper.deleteFile(new File(serverConfig1.getDtoBaseFolderPath()), excludeDtoPaths);
							AutoCreateHelper.deleteFile(new File(serverConfig1.getControllerBaseFolderPath()), excludeFilePaths);
							AutoCreateHelper.deleteFile(new File(serverConfig1.getClientBaseFolderPath()), excludeFilePaths);
							AutoCreateHelper.deleteFile(new File(serverConfig1.getControllerImplBaseFolderPath()), excludeFilePaths);
							AutoCreateHelper.deleteFile(new File(serverConfig1.getServiceBaseFolderPath()), excludeFilePaths);
							AutoCreateHelper.deleteFile(new File(serverConfig1.getServiceImplBaseFolderPath()), excludeFilePaths);
						}

					} catch (Exception var20) {
						throw new Exception("代码清理异常！", var20);
					}
					

					Iterator var27 = serverConfig.getTemplateConfigs().keySet().iterator();

					while (true) {
						if (!var27.hasNext()) {
							continue label206;
						}

						String templateId = (String) var27.next();
						
						templateConfig = (TemplateConfig) serverConfig.getTemplateConfigs().get(templateId);
						
						try {
							(new DtoGenerator(templateConfig)).generate();
							(new ControllerGenerator(templateConfig)).generate();
							(new ClientGenerator(templateConfig)).generate();
							(new ControllerImplGenerator(templateConfig)).generate();
							(new ServiceGenerator(templateConfig)).generate();
							(new ServiceImplGenerator(templateConfig)).generate();
						} catch (Exception var19) {
							throw new Exception("[" + templateConfig.getSheetIndex() + "]-"
									+ templateConfig.getXssfSheet().getSheetName() + "-" + templateId + "-代码生成异常！",
									var19);
						}
					}
				}

				throw new Exception("AutoCreateTemplateCoder.ExcelConfig配置不正确！未找到服务目录：" + serverPath);
			} catch (Exception var23) {
				throw new Exception(template.excelName + "-处理异常！", var23);
			}
		}
	}
}