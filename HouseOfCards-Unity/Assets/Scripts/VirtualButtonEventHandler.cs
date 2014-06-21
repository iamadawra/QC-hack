/*============================================================================== 
 * Copyright (c) 2012-2014 Qualcomm Connected Experiences, Inc. All Rights Reserved. 
 * ==============================================================================*/

using UnityEngine;
using System.Collections.Generic;

/// <summary>
/// This class implements the IVirtualButtonEventHandler interface and
/// contains the logic to swap materials for the teapot model depending on what 
/// virtual button has been pressed.
/// </summary>
public class VirtualButtonEventHandler : MonoBehaviour,
                                         IVirtualButtonEventHandler
{
   
	#region PUBLIC_MEMBER_VARIABLES

    /// <summary>
    /// The materials that will be set for the teapot model
    /// </summary>
    public Material[] m_TeapotMaterials;

    #endregion // PUBLIC_MEMBER_VARIABLES



    #region PRIVATE_MEMBER_VARIABLES
    
    private GameObject mTeapot;
    private List<Material> mActiveMaterials;

    #endregion // PRIVATE_MEMBER_VARIABLES

	static AndroidJavaObject javaTest = null;
	static AndroidJavaObject playerActivityContext = null;

    #region UNITY_MONOBEHAVIOUR_METHODS

    void Start()
    {
        // Register with the virtual buttons TrackableBehaviour
        VirtualButtonBehaviour[] vbs = GetComponentsInChildren<VirtualButtonBehaviour>();
        for (int i = 0; i < vbs.Length; ++i)
        {
            vbs[i].RegisterEventHandler(this);
        }

        // Get handle to the teapot object
        mTeapot = transform.FindChild("teapot").gameObject;

        // The list of active materials
        mActiveMaterials = new List<Material>();
    }

    #endregion // UNITY_MONOBEHAVIOUR_METHODS




    #region PUBLIC_METHODS
    
    /// <summary>
    /// Called when the virtual button has just been pressed:
    /// </summary>
    public void OnButtonPressed(VirtualButtonAbstractBehaviour vb)
    {
        Debug.Log("OnButtonPressed::" + vb.VirtualButtonName);

        if (!IsValid())
        {
            return;
        }


        // Add the material corresponding to this virtual button
        // to the active material list:
        switch (vb.VirtualButtonName)
        {
            case "red":
                mActiveMaterials.Add(m_TeapotMaterials[0]);
                break;

            case "blue":
                mActiveMaterials.Add(m_TeapotMaterials[1]);
                break;

            case "yellowR":
                mActiveMaterials.Add(m_TeapotMaterials[2]);
                break;

			case "yellowL":

			
			using (AndroidJavaClass bryceClass = new AndroidJavaClass("com."))
			{
				
			}
	                break;
        }

        // Apply the new material:
        if (mActiveMaterials.Count > 0)
            mTeapot.renderer.material = mActiveMaterials[mActiveMaterials.Count - 1];
    }


    /// <summary>
    /// Called when the virtual button has just been released:
    /// </summary>
    public void OnButtonReleased(VirtualButtonAbstractBehaviour vb)
    {
        if (!IsValid())
        {
            return;
        }

        // Remove the material corresponding to this virtual button
        // from the active material list:
        switch (vb.VirtualButtonName)
        {
            case "red":
                mActiveMaterials.Remove(m_TeapotMaterials[0]);
                break;

            case "blue":
                mActiveMaterials.Remove(m_TeapotMaterials[1]);
                break;

			case "yellowR":
				mActiveMaterials.Remove(m_TeapotMaterials[2]);
	                break;

			case "yellowL":
				using(AndroidJavaClass actClass = new AndroidJavaClass("com.unity3d.player.unityPlayer"))
				{
					playerActivityContext = actClass.GetStatic<AndroidJavaObject>("currentActivity");
				}
				using (AndroidJavaClass bryceClass = new AndroidJavaClass("com.qualcomm.houseofcards.HelloWorld"))
				{
					javaTest = bryceClass.CallStatic<AndroidJavaObject>("instance");
					javaTest.Call("setContext", playerActivityContext);
				}
			break;
        }

        // Apply the next active material, or apply the default material:
        if (mActiveMaterials.Count > 0)
            mTeapot.renderer.material = mActiveMaterials[mActiveMaterials.Count - 1];
        else
            mTeapot.renderer.material = m_TeapotMaterials[4];
    }


    private bool IsValid()
    {
        // Check the materials and teapot have been set:
        return  m_TeapotMaterials != null &&
                m_TeapotMaterials.Length == 5 &&
                mTeapot != null;
    }

    #endregion // PUBLIC_METHODS
}
