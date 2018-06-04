package com.scyun.utility.regex;

import java.util.Optional;

public interface PatternChecker {
    	public boolean isValid(); 
    	public Optional<String[]> extractGroupData(); 
}
