using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class move1 : MonoBehaviour {
	public Animator anim;
	// Use this for initialization
	void Start () {
		anim = GetComponent<Animator>();
	}
	int x,y = 0;
	int face = 1;
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
			position.y=position.y + 2;
			this.transform.position = position;
		} else if (y == -1) {
			Vector3 position = this.transform.position;
			position.y=position.y - 2;
			this.transform.position = position;
		}

		if (x == 1) {
			Vector3 position = this.transform.position;
			position.x = position.x + 2;
			this.transform.position = position;

			anim.Play ("player_walk_right");
			/*anim.SetBool ("isWalking2",true);
			anim.SetBool ("isWalking1",false);
			anim.SetBool ("isIdle1",false);
			anim.SetBool ("isIdle2",false);*/
			face = -1;
		} else if (x == -1) {
			Vector3 position = this.transform.position;
			position.x = position.x - 2;
			this.transform.position = position;
			/*anim.SetBool ("isWalking1",true);
			anim.SetBool ("isWalking2",false);
			anim.SetBool ("isIdle1",false);
			anim.SetBool ("isIdle2",false);*/
			anim.Play ("player_walk_left");
			face = 1;
		} else {
			/*anim.SetBool ("isWalking1",false);
			anim.SetBool ("isWalking2",false);
			anim.SetBool ("isIdle1",true);
			anim.SetBool ("isIdle2",false);*/
			if (y == 0) {
				if (face == 1) {
					anim.Play ("player_idle_left");
				} else {
					anim.Play ("player_idle_right");
				}
			} else if (face == 1) {
				anim.Play ("player_walk_left");
			} else if (face == -1) {
				anim.Play ("player_walk_right");			
			}




		}




	}
}
