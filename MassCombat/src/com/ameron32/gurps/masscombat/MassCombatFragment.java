package com.ameron32.gurps.masscombat;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link MassCombatFragment.OnFragmentInteractionListener} interface to handle
 * interaction events.
 * 
 */
public class MassCombatFragment extends Fragment {
  
  private OnFragmentInteractionListener mListener;
  
  public MassCombatFragment() {
    // Required empty public constructor
  }
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    TextView textView = new TextView(getActivity());
    textView.setText(R.string.hello_blank_fragment);
    return textView;
  }
  
  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    try {
      mListener = (OnFragmentInteractionListener) activity;
    }
    catch (ClassCastException e) {
      throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
    }
  }
  
  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }
  
  
  
  
  
  
  
  
  
  
  /**
   * This interface must be implemented by activities that contain this fragment
   * to allow an interaction in this fragment to be communicated to the activity
   * and potentially other fragments contained in that activity.
   * <p>
   * See the Android Training lesson <a href=
   * "http://developer.android.com/training/basics/fragments/communicating.html"
   * >Communicating with Other Fragments</a> for more information.
   */
  public interface OnFragmentInteractionListener {
    
    // TODO: Update argument type and name
    public void onFragmentInteraction(Uri uri);
  }
  // TODO: Rename method, update argument and hook method into UI event
  public void onButtonPressed(Uri uri) {
    if (mListener != null) {
      mListener.onFragmentInteraction(uri);
    }
  }  
}
