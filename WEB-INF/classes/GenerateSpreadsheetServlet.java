import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.cbigames.satisfactorsheets.Generator;
import org.cbigames.satisfactorsheets.Recipe;
import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GenerateSpreadsheetServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject data = loadJSONObject(request.getInputStream());
		
		String recipeOutput = data.getString("recipe");
		
		if(recipeOutput == null) {
			response.setStatus(404);
			System.out.println("param not found");
			return;
		}
		//find the corresponding recipe
		Recipe r = null;
		for(int i=0;i<Generator.recipes.length;i++) {
			if(Generator.recipes[i].getOutputItem().equals(recipeOutput)) {
				r = Generator.recipes[i];
				break;
			}
		}
		if(r == null) {
			response.setStatus(404);
			System.out.println("No!");
			return;
		}
		response.setContentType(getServletContext().getMimeType("file.xlsx"));
		response.setHeader("Content-Disposition", "attachment; filename=\"" + recipeOutput + ".xlsx\"");
		//response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		//TODO: figure out how to get content length from this situation
		Generator.generate(r, response.getOutputStream());


	}
	
	static JSONObject loadJSONObject(InputStream in) throws IOException {
		StringBuilder rawContent = new StringBuilder();
        int bytesRead;
        byte[] buffer = new byte[1024];
        while((bytesRead = in.read(buffer)) != -1) {
            rawContent.append(new String(buffer, 0, bytesRead));
        }

        return new JSONObject(rawContent.toString());
	}
}
