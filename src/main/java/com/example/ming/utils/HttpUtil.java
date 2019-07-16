package com.example.ming.utils;

import com.example.ming.common.bean.ResponseCode;
import com.example.ming.common.exception.RequestException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HTTP请求工具类
 * @author hejun
 *
 */
@Slf4j
public class HttpUtil {

	public static final String METHOD_GET = "get";

	public static final String METHOD_POST = "post";

	public static final String PARAM_TYPE_JSON = MediaType.APPLICATION_JSON;

	public static final String PARAM_TYPE_FORM = MediaType.APPLICATION_FORM_URLENCODED;

	public static HttpClient getHttpClient() {
		RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
		return HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
	}

	public static String request(String url) {
		return request(url, METHOD_GET, null, PARAM_TYPE_FORM);
	}

	public static String request(HttpClient httpClient, String url) {
		return request(httpClient, url, METHOD_GET, null, PARAM_TYPE_FORM, null, null);
	}

	public static String request(String url, BasicHeader[] headers) {
		return request(null, url, METHOD_GET, null, PARAM_TYPE_FORM, headers, null);
	}

	public static String request(HttpClient httpClient, String url, BasicHeader[] headers) {
		return request(httpClient, url, METHOD_GET, null, PARAM_TYPE_FORM, headers, null);
	}

	public static String request(String url, String method, Object param) {
		return request(url, method, param, PARAM_TYPE_FORM);
	}

	public static String request(String url, String method, Object param, BasicHeader[] headers) {
		return request(url, method, param, PARAM_TYPE_FORM);
	}

	public static String request(HttpClient httpClient, String url, String method, Object param) {
		return request(httpClient, url, method, param, PARAM_TYPE_FORM, null, null);
	}

	public static String request(HttpClient httpClient, String url, String method, Object param,
								 BasicHeader[] headers) {
		return request(httpClient, url, method, param, PARAM_TYPE_FORM, headers, null);
	}

	public static String request(String url, String method, Object param, String paramType) {
		return request(null, url, method, param, paramType, null, null);
	}

	public static String request(HttpClient httpClient, String url, String method, Object param, String paramType) {
		return request(httpClient, url, method, param, paramType, null, null);
	}

	public static String request(String url, String method, Object param, String paramType,
								 BasicHeader[] headers) {
		return request(null, url, method, param, paramType, headers, null);
	}

	public static String postForm(String url, Object param) {
		return request(null, url, METHOD_POST, param, PARAM_TYPE_FORM, null, null);
	}

	public static String postJson(String url, Object param) {
		return request(null, url, METHOD_POST, param, PARAM_TYPE_JSON, null, null);
	}

	public static String getForm(String url, Object param) {
		return request(null, url, METHOD_GET, param, PARAM_TYPE_FORM, null, null);
	}

	public static String getJson(String url, Object param) {
		return request(null, url, METHOD_GET, param, PARAM_TYPE_JSON, null, null);
	}

	public static byte[] postJson2Bytes(String url, Object param) throws UnsupportedEncodingException {
		ResponseHandler<byte[]> httpHandler = bytesHandler();

		return request(null, url, METHOD_POST, param, PARAM_TYPE_JSON, null, httpHandler);
	}

	public static byte[] getBytes(String url) throws UnsupportedEncodingException {
		ResponseHandler<byte[]> httpHandler = bytesHandler();

		return request(null, url, METHOD_GET, null, PARAM_TYPE_FORM, null, httpHandler);
	}

	public static byte[] getBytes(String url, Object param) throws UnsupportedEncodingException {
		ResponseHandler<byte[]> httpHandler = bytesHandler();

		return request(null, url, METHOD_POST, param, PARAM_TYPE_FORM, null, httpHandler);
	}

	public static <T> T request(HttpClient httpClient, String url,
								 String method, Object param, String paramType,
								 BasicHeader[] headers, ResponseHandler<T> httpHandler) {
		try {
			if (httpHandler == null) {
				httpHandler = handler();
			}

			// client config
			RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
			if (httpClient == null) {
				httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
			}
			RequestConfig localConfig = RequestConfig.copy(globalConfig)
					.setCookieSpec(CookieSpecs.STANDARD_STRICT).build();

			if (METHOD_POST.equals(method)) {
				HttpPost request = new HttpPost(url);

				request.setConfig(localConfig);

				if (PARAM_TYPE_JSON.equals(paramType)) {
					String jsonParam = JsonUtil.toString(param);

					StringEntity jsonEntity = new StringEntity(jsonParam,
							ContentType.create(PARAM_TYPE_JSON, Charsets.UTF_8));

					request.setEntity(jsonEntity);
				} else {
					List<NameValuePair> formparams = new ArrayList<NameValuePair>();

					Map<String, Object> paramMap = new HashedMap();

					if (param != null) {
						if (param instanceof Map) {
							paramMap = (Map<String, Object>) param;
						} else {
							paramMap = JsonUtil.toMap(JsonUtil.toString(param));
						}
					}

					for (String key : paramMap.keySet()) {
						formparams.add(new BasicNameValuePair(key, String.valueOf(paramMap.get(key))));
					}
					UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "utf-8");

					request.setEntity(entity);
				}

				if (headers != null && headers.length > 0) {
					request.setHeaders(headers);
				}

				return httpClient.execute(request, httpHandler);
			} else {
				if (param != null) {
					if (PARAM_TYPE_JSON.equals(paramType)) {
						url = url + "?" + JsonUtil.toString(param);
					} else {
						url = url + "?" + parseFormParam(param);
					}
				}

				HttpGet request = new HttpGet(url);
				request.setConfig(localConfig);

				if (headers != null && headers.length > 0) {
					request.setHeaders(headers);
				}

				return httpClient.execute(request, httpHandler);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("第三方请求失败，失败原因:{}", e.getMessage());
			throw new RequestException(ResponseCode.ERROR_HTTP);
			//			throw new RequestException(ResponseCode.ERROR_HTTP);
		}
	}

	public static void main(String[] args) {
		BasicHeader headers[] = {new BasicHeader("Host", "oapi.dingtalk.com")};
		String res = request("https://140.205.134.70:443/sns/gettoken", headers);

		System.out.println(res);
	}

	private static String parseFormParam(Object param) {
		try {
			Map<String, Object> paramMap = new HashedMap();

			if (param instanceof Map) {
				paramMap = (Map<String, Object>) param;
			} else {
				paramMap = JsonUtil.toMap(JsonUtil.toString(param));
			}

			String formParams = "";

			for (String key : paramMap.keySet()) {
				String val = "";
				Object value = paramMap.get(key);
				if (value != null) {
					if (value instanceof String) {
						val = String.valueOf(value);
					} else {
						val = JsonUtil.toString(value);
					}

					try {
						formParams += key + "=" + URLEncoder.encode(val, "utf-8") + "&";
					} catch (UnsupportedEncodingException e) {
						//
					}
				}
			}

			return StringUtils.chop(formParams);
		} catch (Exception e) {
			return "";
		}
	}

	private static <T> ResponseHandler<T> handler() {
		ResponseHandler<T> loginHandler = new ResponseHandler<T>() {
			@SuppressWarnings("unchecked")
			@Override
			public T handleResponse(final HttpResponse response) throws IOException {
				StatusLine statusLine = response.getStatusLine();
				HttpEntity entity = response.getEntity();
				if (statusLine.getStatusCode() >= 300) {
					throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
				}
				if (entity == null) {
					throw new ClientProtocolException("Response contains no content");
				}
				entity = new BufferedHttpEntity(entity);
				String responseAsString = EntityUtils.toString(entity, "UTF-8");
				return (T) responseAsString;
			}
		};
		return loginHandler;
	}

	private static ResponseHandler<byte[]> bytesHandler() {
		ResponseHandler<byte[]> loginHandler = new ResponseHandler<byte[]>() {
			@SuppressWarnings("unchecked")
			@Override
			public byte[] handleResponse(final HttpResponse response) throws IOException {
				StatusLine statusLine = response.getStatusLine();
				HttpEntity entity = response.getEntity();
				if (statusLine.getStatusCode() >= 300) {
					throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
				}
				if (entity == null) {
					throw new ClientProtocolException("Response contains no content");
				}
				entity =  new BufferedHttpEntity(entity);

				return EntityUtils.toByteArray(entity);
			}
		};
		return loginHandler;
	}

    public static String upload(String url, Map param, byte[] fb) throws Exception {
		ResponseHandler<String> httpHandler = handler();
		RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build();
		HttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();

		RequestConfig localConfig = RequestConfig.copy(globalConfig)
				.setCookieSpec(CookieSpecs.STANDARD_STRICT).build();

		HttpPost request = new HttpPost(url);
		request.setConfig(localConfig);

		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create()
				.addBinaryBody("file", fb, ContentType.TEXT_PLAIN, "file.csv");

		param.forEach((k, v) -> {
			multipartEntityBuilder.addTextBody(String.valueOf(k), String.valueOf(v), ContentType.APPLICATION_FORM_URLENCODED);
//			multipartEntityBuilder.addPart(FormBodyPartBuilder.create(String.valueOf(k),
//					new StringBody(String.valueOf(v), ContentType.APPLICATION_FORM_URLENCODED)).build());
		});

		HttpEntity reqEntity = multipartEntityBuilder.build();

		request.setEntity(reqEntity);

		return httpClient.execute(request, httpHandler);
	}
}
