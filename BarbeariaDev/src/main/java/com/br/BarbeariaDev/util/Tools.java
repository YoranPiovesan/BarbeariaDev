package com.br.BarbeariaDev.util;

import org.apache.commons.codec.binary.Base64;

public class Tools {
	public String decodeBase64(String enconded) {
		return new String(Base64.decodeBase64(enconded));
	}
}
