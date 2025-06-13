<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Models\Player;
use App\Models\GameMatch;
use App\Models\Transaction;
use App\Models\News;
use App\Models\User;
use App\Http\Controllers\PlayerController;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "api" middleware group. Make something great!
|
*/

// Players Routes
Route::get('/players', [PlayerController::class, 'index']);
Route::post('/players', [PlayerController::class, 'store']);
Route::get('/players/{member_code}', [PlayerController::class, 'show']);
Route::put('/players/{member_code}', [PlayerController::class, 'update']);
Route::delete('/players/{member_code}', [PlayerController::class, 'destroy']);



// Authentication Routes
// Route::post('/login', function (Request $request) {
//     $request->validate([
//         'email' => 'required|email',
//     ]);

//     $user = User::where('email', $request->email)->first();
    
//     if (!$user) {
//         return response()->json(['message' => 'User not found'], 404);
//     }

//     $token = $user->createToken('auth_token')->plainTextToken;

//     return response()->json([
//         'access_token' => $token,
//         'token_type' => 'Bearer',
//         'user' => $user
//     ]);
// });

// // Protected Routes
// Route::middleware('auth:sanctum')->group(function () {
//     // User Profile
//     Route::get('/user', function (Request $request) {
//         return $request->user();
//     });

//     // Players Routes
//     Route::get('/players', function () {
//         return Player::all();
//     });

//     Route::get('/players/{member_code}', function ($member_code) {
//         return Player::where('member_code', $member_code)->firstOrFail();
//     });

//     // Matches Routes
//     Route::get('/matches', function () {
//         return GameMatch::with(['playerOne', 'playerTwo', 'winningPlayer'])->get();
//     });

//     Route::get('/matches/{match_code}', function ($match_code) {
//         return GameMatch::with(['playerOne', 'playerTwo', 'winningPlayer'])
//             ->where('match_code', $match_code)
//             ->firstOrFail();
//     });

//     Route::post('/matches', function (Request $request) {
//         $validated = $request->validate([
//             'match_code' => 'required|unique:game_matches',
//             'type' => 'required|in:single,double',
//             'player1' => 'required_if:type,single',
//             'player2' => 'required_if:type,single',
//             'team1' => 'required_if:type,double',
//             'team2' => 'required_if:type,double',
//             'datetime' => 'required|date'
//         ]);

//         return GameMatch::create($validated);
//     });

//     // Transactions Routes
//     Route::get('/transactions', function () {
//         return Transaction::with('player')->get();
//     });

//     Route::get('/transactions/{transaction_code}', function ($transaction_code) {
//         return Transaction::with('player')
//             ->where('transaction_code', $transaction_code)
//             ->firstOrFail();
//     });

//     Route::post('/transactions', function (Request $request) {
//         $validated = $request->validate([
//             'transaction_code' => 'required|unique:transactions',
//             'member_code' => 'required|exists:players,member_code',
//             'type' => 'required|in:monthly_fee,penalty,donation',
//             'amount' => 'required|numeric',
//             'note' => 'nullable|string'
//         ]);

//         return Transaction::create($validated);
//     });

//     // News Routes
//     Route::get('/news', function () {
//         return News::with('author')->get();
//     });

//     Route::get('/news/{news_code}', function ($news_code) {
//         return News::with('author')
//             ->where('news_code', $news_code)
//             ->firstOrFail();
//     });

//     Route::post('/news', function (Request $request) {
//         $validated = $request->validate([
//             'news_code' => 'required|unique:news',
//             'title' => 'required|string',
//             'content' => 'required|string',
//             'image' => 'nullable|string',
//             'author_uid' => 'required|exists:users,id'
//         ]);

//         $validated['timestamp'] = now();

//         return News::create($validated);
//     });

//     // Admin Only Routes
//     Route::middleware(function ($request, $next) {
//         if (!$request->user()->isAdmin()) {
//             return response()->json(['message' => 'Unauthorized'], 403);
//         }
//         return $next($request);
//     })->group(function () {
//         // Admin specific routes here
//         Route::post('/players', function (Request $request) {
//             $validated = $request->validate([
//                 'member_code' => 'required|unique:players',
//                 'username' => 'required|string',
//                 'avatar' => 'nullable|string',
//                 'birthday' => 'required|date',
//                 'hometown' => 'required|string',
//                 'residence' => 'required|string'
//             ]);

//             return Player::create($validated);
//         });

//         Route::put('/players/{member_code}', function (Request $request, $member_code) {
//             $player = Player::where('member_code', $member_code)->firstOrFail();
            
//             $validated = $request->validate([
//                 'username' => 'string',
//                 'avatar' => 'nullable|string',
//                 'birthday' => 'date',
//                 'hometown' => 'string',
//                 'residence' => 'string'
//             ]);

//             $player->update($validated);
//             return $player;
//         });
//     });
// }); 