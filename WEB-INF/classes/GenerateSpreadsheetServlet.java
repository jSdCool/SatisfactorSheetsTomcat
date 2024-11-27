import java.io.IOException;
import java.io.InputStream;

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
		//load the post data into a json object
		JSONObject data = loadJSONObject(request.getInputStream());
		//read the recipe data filed
		String recipeOutput = data.getString("recipe");
		//if it does not exist then return 404
		if(recipeOutput == null) {
			response.setStatus(404);
			//System.out.println("param not found");
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
		//if the recipe was not found then return 404
		if(r == null) {
			response.setStatus(404);
			System.out.println("No!");
			return;
		}
		//set response headers
		response.setContentType(getServletContext().getMimeType("file.xlsx"));
		response.setHeader("Content-Disposition", "attachment; filename=\"" + recipeOutput + ".xlsx\"");
		
		//generate the sheet and send it to the response
		//TODO: figure out how to get content length from this situation
		Generator.generate(r, response.getOutputStream());


	}
	
	//helper function to load the json from the post data
	//TODO: move this to com.cbigames.satisfactorsheets.Util
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
