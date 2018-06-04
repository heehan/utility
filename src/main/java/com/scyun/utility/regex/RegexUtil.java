package com.scyun.utility.regex;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 정규식 유틸리티
 * 자주 사용되는 정규식 패턴을 모으고
 * 쉽게 사용할 수 있도록  (예 전화번호 페턴,휴대전화 번호패턴 등등)
 * @author yunhuihan
 *
 */
public class RegexUtil implements SourceSetter, PatternSetter, PatternChecker
{
	public static final String CAR_SERIAL_PATTERN = "(서울|부산|대구|인천|광주|대전|울산|경기|강원|충북|충남|전북|전남|경북|경남|제주){0,1}([0-9]{2})([ㄱ-ㅎ가-힣ㅏ-ㅣ]{1})(([0-9]{4})$)";

	private Logger logger = LoggerFactory.getLogger(getClass());
    private String source;
    private String patternStr;
    private Pattern pattern = null;
    private Boolean isFind = null;
    private Matcher matcher = null;
    
    public static SourceSetter newInstance()
    {
    	return new RegexUtil();
    }
    
    private RegexUtil() { }
    
	@Override
	public PatternSetter setSource(String regexSource) {
		logger.debug("SOURCE -> {}" , regexSource);
		this.source = regexSource;
		return this;
	}
	@Override
	public PatternChecker setPattern(String regexPattern) {
		logger.debug("REGEX_PATTERN -> {}" , regexPattern);
		this.patternStr = regexPattern;
		this.pattern = Pattern.compile(this.patternStr);
        this.matcher = this.pattern.matcher(this.source);
		return this;
	}
    @Override
    public boolean isValid()
    {
    	if (this.isFind == null)
    	{
    		this.isFind = this.matcher.find();
    	}
		logger.debug("IS_VALID -> {}" , this.isFind);
        return this.isFind;
    }
    

	@Override
	public Optional<String[]> extractGroupData() {

        String[] groupData = null;
        if (isValid())
        {
            int groupCnt = matcher.groupCount();
            groupData = new String[groupCnt - 1];
            
            for (int i=1;i<groupCnt;++i)
            {
            	logger.debug("GROUP_DATA => IDX = {}, findString = {}", i, matcher.group(i));
                groupData[i-1] = matcher.group(i);
            }
        }
        return Optional.ofNullable(groupData);
	}
	
	public static void main(String args[]) throws Exception
	{
		Logger logger = LoggerFactory.getLogger("MAIN");
		
		Optional<String[]> groupData = RegexUtil.newInstance()
			                                 	.setSource("서울49러1212")
			                                 	.setPattern(RegexUtil.CAR_SERIAL_PATTERN)
			                                 	.extractGroupData();
		
		if (groupData.isPresent())
		{
			for (String str : groupData.get())
			{
				logger.info("MAIN -> DATA ->> {}", str );
			}
		}
	}


}

