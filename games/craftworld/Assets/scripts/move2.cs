using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class move2 : MonoBehaviour {
	Vector2 _target = new Vector2 (100,100);
	Vector2 _from = new Vector2 (0, 0);
	float _moveTime = 2;
	float _timeCount = 0;
	// Use this for initialization









	void Start () {
		




	}
	void Update () {
		_timeCount += Time.deltaTime;
		this.transform.position=Vector2.Lerp(_from,_target,_timeCount/_moveTime);

		}
	// Use this for initialization

}
