package com.mycore.thebe.testrun;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import com.mycore.thebe.common.util.FilingUtils;

public class VMTest {

	public static void main(String[] args) {
		try {
			Template template = FilingUtils.getTemplate("/usr/vm_test.vm");
			
			VelocityContext ctx = new VelocityContext();
			
			ctx.put("name", "THEBE");
			StringWriter writer = new StringWriter();
			template.merge(ctx, writer);
			
			System.out.println(writer.toString());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
