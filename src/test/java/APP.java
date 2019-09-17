import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

import net.sf.json.JSONArray;

public class APP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Map<String, Object> map=new HashMap<String, Object>();
		Long i=10L;
		Integer t=10;
		map.put("i", i);
		map.put("t", t);
		for(String key:map.keySet())
		{
			if(!StringUtils.isEmpty(map.get(key)) && map.get(key) instanceof Long)
			{
				map.put(key,map.get(key).toString());
			}
		}
		JSONArray list = JSONArray.fromObject(map);
		System.out.println(list.toString());
		
	}

}
