package unidades.unidad2.ejemplos.tanqueagua;

class WaterTank {
	final float capacity;
	float currentVolume = 0.0f;
	WaterTank overflow;

	WaterTank(float cap) {
		capacity = cap;
	}

	void addWater(float amount) throws OverflowException {
	}

	void removeWater(float amount) throws UnderflowException {
	}
}
