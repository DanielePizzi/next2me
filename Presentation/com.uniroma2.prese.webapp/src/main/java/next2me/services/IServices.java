package next2me.services;

import next2me.model.response.GetPointResponse;
import next2me.model.response.RemovePointResponse;
import next2me.persistent.entity.User;

public interface IServices {
	public User login(String email);
	public void savePoint(String username, String nome, String citta, String stato, String lat, String lng, String tipo, String descrizione);
	public GetPointResponse getPoint(String username, String categoria, double lotitudine, double langitudine);
	public RemovePointResponse removePoint(int idPoint);
}
