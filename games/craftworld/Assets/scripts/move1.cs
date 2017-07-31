using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class move1 : MonoBehaviour {

	// Use this for initialization
	void Start () {
		
	}
	int x,y = 0;
	// Update is called once per frame
	void Update () {
		if (Input.GetKeyDown (KeyCode.UpArrow)) {
			y += 1;
		}
			

		if (Input.GetKeyUp (KeyCode.UpArrow)) {
			y -= 1;
		}

		if (Input.GetKeyDown (KeyCode.DownArrow)) {
			y -= 1;
		}


		if (Input.GetKeyUp (KeyCode.DownArrow)) {
			y += 1;
		}

		if (Input.GetKeyDown (KeyCode.LeftArrow)) {
			x -= 1;
		}


		if (Input.GetKeyUp (KeyCode.LeftArrow)) {
			x += 1;
		}

		if (Input.GetKeyDown (KeyCode.RightArrow)) {
			x += 1;
		}


		if (Input.GetKeyUp (KeyCode.RightArrow)) {
			x -= 1;
		}





		if (y == 1) {
			Vector3 position = this.transform.position;
			position.y++;
			this.transform.position = position;
		} else if (y == -1) {
			Vector3 position = this.transform.position;
			position.y--;
			this.transform.position = position;
		}

		if (x == 1) {
			Vector3 position = this.transform.position;
			position.x=position.x + 1;
			this.transform.position = position;
		} else if (x == -1) {
			Vector3 position = this.transform.position;
			position.x--;
			this.transform.position = position;
		}




	}
}
