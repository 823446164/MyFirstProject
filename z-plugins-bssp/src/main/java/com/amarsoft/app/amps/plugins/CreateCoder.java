package com.amarsoft.app.amps.plugins;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.amarsoft.amps.dct.excel.ExcelCoder;
import com.amarsoft.amps.dct.excel.serviceandtemplate.config.ServiceConstants;
import com.amarsoft.amps.dct.excel.serviceandtemplate.config.TemplateConstants;
import com.amarsoft.amps.dct.excel.serviceandtemplate.generator.service.ClientGenerator;
import com.amarsoft.amps.dct.excel.serviceandtemplate.generator.service.ControllerGenerator;
import com.amarsoft.amps.dct.excel.serviceandtemplate.generator.service.ControllerImplGenerator;
import com.amarsoft.amps.dct.excel.serviceandtemplate.generator.service.DtoGenerator;
import com.amarsoft.amps.dct.excel.serviceandtemplate.pojo.service.ServerConfig;
import com.amarsoft.amps.dct.excel.serviceandtemplate.pojo.service.ServiceConfig;
import com.amarsoft.amps.dct.excel.serviceandtemplate.utils.AutoCreateHelper;

public class CreateCoder {
	public static Map<String, ServerConfig> serverConfigs = new LinkedHashMap();
	public static List<String> excludeFilePaths = new LinkedList();
	public static List<String> excludeDtoPaths = new LinkedList();

	public static void create(ExcelCoder[] coders) throws Exception {
		InputStream is = null;
		XSSFWorkbook xssfWorkbook = null;
		ExcelCoder[] var3 = coders;
		int var4 = coders.length;

		label213 : for (int var5 = 0; var5 < var4; ++var5) {
			ExcelCoder coder = var3[var5];

			try {
				ServerConfig serverConfig = (ServerConfig) serverConfigs.get(coder.serverId);
				if (serverConfig == null) {
					serverConfig = new ServerConfig();
					serverConfig.setServerId(coder.serverId);
					serverConfig.setDistributed(coder.isDistributed);
					serverConfig.setDtoBasePackage(coder.applicationPackage + coder.serverId + ".cs.dto");
					serverConfig.setControllerBasePackage(coder.applicationPackage + coder.serverId + ".controller");
					serverConfig.setControllerImplBasePackage(
							coder.applicationPackage + coder.serverId + ".controller.impl");
					serverConfig.setClientBasePackage(coder.applicationPackage + coder.serverId + ".cs.client");
					serverConfig.setDtoBaseFolderPath(ServiceConstants.BASE_PATH + "ems-" +coder.serverId + "-cs"
							+ "/src/main/java/" + serverConfig.getDtoBasePackage().replaceAll("\\.", "/") + "/");
					serverConfig.setControllerBaseFolderPath(ServiceConstants.BASE_PATH + "ems-" +coder.serverId + "-cs"
							+ "/src/main/java/" + serverConfig.getControllerBasePackage().replaceAll("\\.", "/") + "/");
					serverConfig.setControllerImplBaseFolderPath(
							ServiceConstants.BASE_PATH + "ems-" +coder.serverId + "/src/main/java/"
									+ serverConfig.getControllerImplBasePackage().replaceAll("\\.", "/") + "/");
					serverConfig.setClientBaseFolderPath(ServiceConstants.BASE_PATH + "ems-" +coder.serverId + "-cs"
							+ "/src/main/java/" + serverConfig.getClientBasePackage().replaceAll("\\.", "/") + "/");
					serverConfigs.put(coder.serverId, serverConfig);
				}

				String serverPath = TemplateConstants.BASE_PATH + "ems-" +coder.serverId;
				File file = new File(serverPath);
				if (file.exists() && file.isDirectory()) {
					is = new FileInputStream(TemplateConstants.RESOURCE_PATH + coder.excelName);
					xssfWorkbook = new XSSFWorkbook(is);
					int i = coder.beginSheetIndex;

					while (true) {
						if (i >= xssfWorkbook.getNumberOfSheets()) {
							continue label213;
						}

						XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(i);

						try {
							if (serverConfig.getServiceConfigs().containsKey(
									AutoCreateHelper.getCellString(xssfSheet, ServiceConstants.MODEL_INDEX))) {
								throw new Exception(serverConfig.getServerId() + "重复的服务："
										+ AutoCreateHelper.getCellString(xssfSheet, ServiceConstants.MODEL_INDEX));
							}

							ServiceConfig serviceConfig = new ServiceConfig(serverConfig, xssfSheet, i);
							serverConfig.getServiceConfigs().put(serviceConfig.getHeader().getServiceId(),
									serviceConfig);
						} catch (Exception var21) {
							xssfWorkbook.close();
							throw new Exception(
									"[" + i + "]-" + coder.excelName + "-" + xssfSheet.getSheetName() + "-数据解析异常！",
									var21);
						}

						++i;
					}
				}

				throw new Exception("AutoCreateCoder.ExcelConfig配置不正确！未找到服务目录：" + serverPath);
			} catch (Exception var22) {
				throw new Exception(coder.excelName + "处理异常！", var22);
			} finally {
				if (xssfWorkbook != null) {
					xssfWorkbook.close();
				}

				if (is != null) {
					is.close();
				}

			}
		}

		Iterator var24 = serverConfigs.keySet().iterator();

		String key;
		ServerConfig serverConfig;
		// 清理代码
		while (var24.hasNext()) {
			key = (String) var24.next();
			serverConfig = (ServerConfig) serverConfigs.get(key);
			AutoCreateHelper.deleteFile(new File(serverConfig.getDtoBaseFolderPath()), excludeDtoPaths);
			AutoCreateHelper.deleteFile(new File(serverConfig.getControllerBaseFolderPath()), excludeFilePaths);
			AutoCreateHelper.deleteFile(new File(serverConfig.getClientBaseFolderPath()), excludeFilePaths);
			AutoCreateHelper.deleteFile(new File(serverConfig.getControllerImplBaseFolderPath()), excludeFilePaths);
		}
		// 生成代码
		var24 = serverConfigs.keySet().iterator();
		while (var24.hasNext()) {
			key = (String) var24.next();
			serverConfig = (ServerConfig) serverConfigs.get(key);

			try {
				Iterator var27 = serverConfig.getServiceConfigs().keySet().iterator();

				while (var27.hasNext()) {
					String modelId = (String) var27.next();
					ServiceConfig serviceConfig = (ServiceConfig) serverConfig.getServiceConfigs().get(modelId);

					try {
						(new DtoGenerator(serviceConfig)).generate();
						(new ControllerGenerator(serviceConfig)).generate();
						(new ClientGenerator(serviceConfig)).generate();
						(new ControllerImplGenerator(serviceConfig)).generate();
					} catch (Exception var19) {
						throw new Exception("[" + serviceConfig.getSheetIndex() + "]-"
								+ serviceConfig.getXssfSheet().getSheetName() + "-" + modelId + "-代码生成异常！", var19);
					}
				}
			} catch (Exception var20) {
				throw new Exception(serverConfig.getServerId() + "处理异常！", var20);
			}
		}

	}
}